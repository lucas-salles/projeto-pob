package dao;

import java.util.List;
import java.util.TreeMap;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.events.CancellableObjectEventArgs;
import com.db4o.events.CommitEventArgs;
import com.db4o.events.Event4;
import com.db4o.events.EventListener4;
import com.db4o.events.EventRegistry;
import com.db4o.events.EventRegistryFactory;
import com.db4o.events.ObjectContainerEventArgs;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
public class IDAutoIncrementoControle {
	// Faz a geração automatica de IDs para qualquer classe que implementa
	// a interface IDAutoIncrementoInterface
	protected static ObjectContainer manager;
	protected static ObjectContainer managersequencia;
	private static boolean gerounovoid;
	private static boolean bancolocal;	
	private static String ipservidor;
	private static TreeMap<String,Sequencia> sequencias = new TreeMap<String,Sequencia>();


	public static void iniciar(ObjectContainer m){
		manager = m;
		//identificar o tipo de conexão: local ou cliente/servidor
		bancolocal = manager instanceof EmbeddedObjectContainer;
		EventRegistry eventRegistry = EventRegistryFactory.forObjectContainer(manager);

		//criar banco sequencia.db4o para guardar as sequencias
		if(bancolocal) {
			EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
			config.common().messageLevel(0);  // 0,1,2,3...
			managersequencia = Db4oEmbedded.openFile(config, "sequencia.db4o");
		}
		else {
			ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
			config.common().messageLevel(0);   //0,1,2,3,4
			managersequencia = Db4oClientServer.openClient(config,ipservidor,35000,"usuario0","senha0");	
		}

		//------------------------------------------
		// ADICIONAR OUVINTE DE EVENTOS DO BANCO DE DADOS
		//------------------------------------------
		// evento gerado antes de persistir um objeto
		eventRegistry.creating().addListener(new EventListener4<CancellableObjectEventArgs>() {
			public void onEvent(Event4<CancellableObjectEventArgs> event4, CancellableObjectEventArgs args) {
				Object objeto = args.object();  //parametro
				
				// verificar se o objeto persistido implementa 
				// a interface IDAutoIncrementoInterface
				if(objeto instanceof IDAutoIncrementoInterface){
					String nomedaclasse = objeto.getClass().getName();
					Sequencia seq = sequencias.get(nomedaclasse);
					if(seq == null) {
						List<Sequencia> resultados = managersequencia.queryByExample(new Sequencia(nomedaclasse));
						if(resultados.size()==0)
							seq = new Sequencia(nomedaclasse); // cria o registro para a classe
						else 
							seq = (Sequencia) resultados.get(0); // carrega o resistro da classe existente

					}
					seq.incrementarUltimoId();
					sequencias.put(nomedaclasse, seq);
					//inserir o novo id dentro do objeto que está sendo persistido
					((IDAutoIncrementoInterface)objeto).setId(seq.getUltimoId()); 

					gerounovoid = true;
					//System.out.println("--->gerando novo id="+seq.getUltimoId()+" para a classe="+nomedaclasse);
				}
			}
		});

		// evento gerado imediatamente antes do commit
		eventRegistry.committing().addListener(new EventListener4<CommitEventArgs>() {
			public  void onEvent(Event4<CommitEventArgs> source,CommitEventArgs arguments) {				//salvar os sequencias alterados no banco
				if (gerounovoid) {
					gerounovoid = false;
					for(Sequencia reg : sequencias.values()) {  //atualizar os sequencias no banco
						managersequencia.store(reg);
						managersequencia.commit();
						if(!bancolocal) 
							managersequencia.ext().purge(reg);  	//excluir do cache 
					}
				}
			}
		});   

		//evento de abertura do banco
		//		eventRegistry.opened().addListener(new EventListener4<ObjectContainerEventArgs>() {
		//			public void onEvent(Event4<ObjectContainerEventArgs> event, ObjectContainerEventArgs args) {
		//				//---------
		//			}
		//		});

		//evento de fechamento do banco principal
		eventRegistry.closing().addListener(new EventListener4<ObjectContainerEventArgs>() {
			public void onEvent(Event4<ObjectContainerEventArgs> event, ObjectContainerEventArgs args) {
				if (managersequencia!= null && !managersequencia.ext().isClosed())
					managersequencia.close();		//fecha o banco de sequencias
			}
		});
	}

	public static void iniciar(ObjectContainer man, String ip){
		ipservidor = ip;
		iniciar(man);
	}


}
//============================================================
class Sequencia{
	//armazena o nome da classe e o ultimo id desta classe
	//============================================================
	private String nomedaclasse;
	private int ultimoid;
	public Sequencia(String nomedaclasse) {
		this.nomedaclasse = nomedaclasse;
		this.ultimoid = 0;
	}		
	public int getUltimoId() {
		return ultimoid;
	}
	public void incrementarUltimoId() {
		ultimoid++;
	}
	public String getNomedaclasse() {
		return nomedaclasse;
	}
	public void setNomedaclasse(String nomedaclasse) {
		this.nomedaclasse = nomedaclasse;
	}
	@Override
	public String toString() {
		return "Sequencia [nomedaclasse=" + nomedaclasse + ", ultimoid=" + ultimoid + "]";
	}		

}

