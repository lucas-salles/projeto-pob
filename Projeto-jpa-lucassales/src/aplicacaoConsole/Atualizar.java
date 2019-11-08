package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Atualizar {
	public Atualizar(){
		Fachada.inicializar();
		atualizar();
		Fachada.finalizar();
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplica��o");
	}

	public void atualizar(){
		System.out.println("Atualizando...");
		try{
			Fachada.alterarCliente("joao", "bruno");
			Fachada.alterarProduto("suco", "suco de laranja");
			Fachada.alterarProdutoTipo("peixe", "carnes");
			Fachada.alterarTipo("bebidas", "bebidas em geral");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//=================================================
	public static void main(String[] args) {
		new Atualizar();
	}
}


