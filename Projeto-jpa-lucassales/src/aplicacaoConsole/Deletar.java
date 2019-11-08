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
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void deletar(){
		System.out.println("Excluindo...");
		try {
			Fachada.excluirCliente("joao");
			Fachada.excluirProduto("bife");
			Fachada.excluirTipo("frutos do mar");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}


