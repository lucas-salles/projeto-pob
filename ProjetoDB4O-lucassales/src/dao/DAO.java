/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package dao;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Conta;
import modelo.Produto;
import modelo.Tipo;


public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open(){	
		if(manager==null){		
			abrirBancoLocal();
			//abrirBancoServidor();
		}
	}
	public static void abrirBancoLocal(){		
		//Backup.criar("banco.db4o");
		//Backup.criar("sequencia.db4o");
		//new File("banco.db4o").delete();  //apagar o banco
		//new File("sequencia.db4o").delete();  //apagar o banco

		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		//config.common().objectClass(Cliente.class).cascadeOnDelete(true);
		config.common().objectClass(Cliente.class).cascadeOnUpdate(true);
		config.common().objectClass(Cliente.class).cascadeOnActivate(true);
		//config.common().objectClass(Conta.class).cascadeOnDelete(true);
		config.common().objectClass(Conta.class).cascadeOnUpdate(true);
		config.common().objectClass(Conta.class).cascadeOnActivate(true);
		//config.common().objectClass(Produto.class).cascadeOnDelete(true);
		config.common().objectClass(Produto.class).cascadeOnUpdate(true);
		config.common().objectClass(Produto.class).cascadeOnActivate(true);
		//config.common().objectClass(Tipo.class).cascadeOnDelete(true);
		config.common().objectClass(Tipo.class).cascadeOnUpdate(true);
		config.common().objectClass(Tipo.class).cascadeOnActivate(true);
		
		
		// 		indexacao de atributos
		config.common().objectClass(Cliente.class).objectField("nome").indexed(true);
		config.common().objectClass(Produto.class).objectField("nome").indexed(true);
		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		
		//geracao automatica de IDs
		IDAutoIncrementoControle.iniciar(manager); 
	}

	public static void abrirBancoServidor(){
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		//config.common().objectClass(Cliente.class).cascadeOnDelete(true);
		config.common().objectClass(Cliente.class).cascadeOnUpdate(true);
		config.common().objectClass(Cliente.class).cascadeOnActivate(true);
		//config.common().objectClass(Conta.class).cascadeOnDelete(true);
		config.common().objectClass(Conta.class).cascadeOnUpdate(true);
		config.common().objectClass(Conta.class).cascadeOnActivate(true);
		//config.common().objectClass(Produto.class).cascadeOnDelete(true);
		config.common().objectClass(Produto.class).cascadeOnUpdate(true);
		config.common().objectClass(Produto.class).cascadeOnActivate(true);
		//config.common().objectClass(Tipo.class).cascadeOnDelete(true);
		config.common().objectClass(Tipo.class).cascadeOnUpdate(true);
		config.common().objectClass(Tipo.class).cascadeOnActivate(true);
		
		// 		indexacao de atributos
		config.common().objectClass(Cliente.class).objectField("nome").indexed(true);
		config.common().objectClass(Produto.class).objectField("nome").indexed(true);

		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
		if (ip==null || ip.isEmpty())	{
			System.out.println("ip invalido");
			System.exit(0);
		}
		manager = Db4oClientServer.openClient(config,ip,34000,"usuario1","senha1");
	
		//geracao automatica de IDs
		IDAutoIncrementoControle.iniciar(manager); 

	}

	public static void close(){
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}

	//----------CRUD-----------------------

	public void create(T obj){
		manager.store( obj );
	}

	public abstract T read(Object chave);

	public T update(T obj){
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	public void refresh(T obj){
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	//--------transação---------------
	public static void begin(){	
	}		// tem que ser vazio

	public static void commit(){
		manager.commit();
	}
	public static void rollback(){
		manager.rollback();
	}



}

