package aplicacaoConsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import fachada.Fachada;


public class Cadastrar {
	public Cadastrar(){
		Fachada.inicializar();
		cadastrar();
		Fachada.finalizar();
	}

	public void cadastrar(){
		System.out.println("Cadastrando...");

		try {
			Fachada.cadastrarTipo("bebidas");
			Fachada.cadastrarTipo("whisky");
			Fachada.cadastrarTipo("vodka");
			Fachada.cadastrarTipo("cerveja");
			Fachada.cadastrarTipo("frutos do mar");
			Fachada.cadastrarTipo("carnes");
			Fachada.cadastrarTipo("sobremesa");
			
			Fachada.cadastrarProduto("suco", 10, "bebidas");
			Fachada.cadastrarProduto("coca", 10, "bebidas");
			Fachada.cadastrarProduto("guaraná", 10, "bebidas");
			Fachada.cadastrarProduto("bourbon", 100, "whisky");
			Fachada.cadastrarProduto("johnnie walker", 130, "whisky");
			Fachada.cadastrarProduto("jack daniels", 130, "whisky");
			Fachada.cadastrarProduto("smirnoff", 80, "vodka");
			Fachada.cadastrarProduto("absolut", 100, "vodka");
			Fachada.cadastrarProduto("skol", 7, "cerveja");
			Fachada.cadastrarProduto("brahma", 7, "cerveja");
			Fachada.cadastrarProduto("camarão", 20, "frutos do mar");
			Fachada.cadastrarProduto("peixe", 20, "frutos do mar");
			Fachada.cadastrarProduto("bife", 5, "carnes");
			Fachada.cadastrarProduto("bolo de chocolate", 10, "sobremesa");
			Fachada.cadastrarProduto("mousse", 8, "sobremesa");
			
			Fachada.cadastrarCliente("joao", "1234");
			Fachada.cadastrarCliente("pedro", "1234");
			Fachada.cadastrarCliente("maria", "1234");
			Fachada.cadastrarCliente("joana", "1234");
			Fachada.cadastrarCliente("jose", "1234");
			
			Fachada.cadastrarConta("joao");
			Fachada.cadastrarConta("pedro");
			Fachada.cadastrarConta("maria");
			Fachada.cadastrarConta("joana");
			Fachada.cadastrarConta("jose");
			
			Fachada.adicionarProduto("joao", "suco");
			Fachada.adicionarProduto("joao", "peixe");
			Fachada.adicionarProduto("joao", "bolo de chocolate");
			Fachada.removerProduto("joao", "suco");
			
			Fachada.adicionarProduto("pedro", "bourbon");
			
			Fachada.adicionarProduto("maria", "brahma");
			Fachada.adicionarProduto("maria", "bife");
			Fachada.adicionarProduto("maria", "guaraná");
			
			Fachada.adicionarProduto("joana", "bourbon");
			Fachada.adicionarProduto("joana", "coca");
			Fachada.adicionarProduto("joana", "mousse");
			
			Fachada.fecharConta("joao");
			Fachada.fecharConta("maria");
			Fachada.fecharConta("joana");
			
			Fachada.cadastrarConta("joao");
			Fachada.adicionarProduto("joao", "smirnoff");
			Fachada.fecharConta("joao");
			
			Fachada.cadastrarConta("joao");
			Fachada.adicionarProduto("joao", "johnnie walker");
			Fachada.fecharConta("joao");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("fim do programa");
	}
	
	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


