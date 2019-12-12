package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Consultar {
	public Consultar(){
		Fachada.inicializar();
		consultar();
		Fachada.finalizar();
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void consultar() {
		System.out.println("\n------CONSULTAS----");
		System.out.println("\n1. os clientes por parte do nome");
		System.out.println(Fachada.consultaClientesPorParteNome("jo"));
		System.out.println("\n2. os clientes que tem 3 contas");
		System.out.println(Fachada.consultaClientesNContas(3));
		System.out.println("\n3. os clientes que consomem whisky");
		System.out.println(Fachada.consultaClientesPorTipo("whisky"));
		System.out.println("\n4. os clientes que compraram 2 produtos ou mais em uma conta");
		System.out.println(Fachada.consultaClientesNProdutos(2));
		System.out.println("\n5. os produtos do tipo bebidas");
		System.out.println(Fachada.consultaProdutoPorTipo("bebidas"));
	}

	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}


