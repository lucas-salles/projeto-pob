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
		System.out.println("fim do programa");
	}

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


