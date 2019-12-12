package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Listar {
	public Listar(){
		Fachada.inicializar();
		listar();
		Fachada.finalizar();
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void listar(){
		try {
			System.out.println(Fachada.listarClientes());
			System.out.println(Fachada.listarContas());
			System.out.println(Fachada.listarContasPorCliente("joao"));
			System.out.println(Fachada.listarProdutos());
			System.out.println(Fachada.listarTipos());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


