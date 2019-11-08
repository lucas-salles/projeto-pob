package aplicacaoDesempenho;

import java.util.Calendar;
import java.util.GregorianCalendar;

import fachada.Fachada;

public class MedirTempo {

	public static void main(String[] args) {
		Calendar hinicial, hfinal;

		Fachada.inicializar();
		//------ gravar dados no banco
		hinicial = new GregorianCalendar();
		System.out.println("\ninicio da gravação " + hinicial.getTime());
		try{
			for (int i = 1; i <= 500; i++) {
				String nome = "teste"+i;
				Fachada.cadastrarCliente(nome, "1324");			
			}
			
			Fachada.cadastrarTipo("cerveja");
			for (int i = 1; i <= 500; i++) {
				String nome = "cerveja"+i;
				Fachada.cadastrarProduto(nome, 10, "cerveja");
			}
		}
		catch(Exception e){
			System.out.println("erro:"+ e.getMessage());
		}
		System.out.println("fim da gravação    " + new GregorianCalendar().getTime());			


		//----ler dados do banco
		System.out.println("\n\ninicio da consulta =  " + new GregorianCalendar().getTime());	
		String s = Fachada.listarClientes();
		String s1 = Fachada.listarProdutos();
	//	System.out.println(s);
		System.out.println("fim da consulta =     " + new GregorianCalendar().getTime());
		hfinal = new GregorianCalendar();

		//---- Calculo do Tempo Total----------
		System.out.println( 
				"\n\n Hora inicial= " +hinicial.getTime() +
				"\n Hora final=   "+ hfinal.getTime() +
				"\n Total (seg)=  "+(hfinal.getTimeInMillis()-hinicial.getTimeInMillis())/1000.0
				);

		Fachada.finalizar();

	}

}


