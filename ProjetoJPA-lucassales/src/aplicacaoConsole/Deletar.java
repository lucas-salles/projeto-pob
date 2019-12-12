package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Deletar {
	public Deletar(){
		Fachada.inicializar();
		deletar();
		Fachada.finalizar();
	}

	public void deletar(){
		System.out.println("Deletando...");
		try {
			Fachada.excluirCliente("joao");
			Fachada.excluirProduto("bife");
			Fachada.excluirTipo("frutos do mar");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("fim do programa");
	}

	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}


