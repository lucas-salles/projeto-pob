package fachada;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOCliente;
import daojpa.DAOConta;
import daojpa.DAOProduto;
import daojpa.DAOTipo;
import modelo.Cliente;
import modelo.Conta;
import modelo.Produto;
import modelo.Tipo;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

public class Fachada {	
	private static DAOCliente daocliente = new DAOCliente();
	private static DAOConta daoconta= new DAOConta();
	private static DAOProduto daoproduto = new DAOProduto();
	private static DAOTipo daotipo = new DAOTipo();

	public static void inicializar(){
		DAO.open();
	}

	public static void finalizar(){
		DAO.close();
	}


	public static Cliente cadastrarCliente(String nome, String cpf)	throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nome);
		if(cliente != null) {
			DAO.rollback();
			throw new Exception("cadastrar cliente - cliente já cadastrado:" + nome);
		}

		cliente = new Cliente(nome, cpf);
		daocliente.create(cliente);
		DAO.commit();
		return cliente;
	}
	
	public static Conta cadastrarConta(String nomecliente)	throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("cadastrar conta - cliente não cadastrado:" + nomecliente);
		}
		
		if(cliente.ultimaContaAtiva() != null) {
			DAO.rollback();
			throw new Exception("cadastrar conta - cliente com conta aberta:" + nomecliente);
		}
		
		Conta conta = new Conta(cliente);
		cliente.adicionar(conta);
		daoconta.create(conta);
		daocliente.update(cliente);
		DAO.commit();
		return conta;
	}
	
	public static Produto cadastrarProduto(String nome, double preco, String nometipo) throws Exception {
		DAO.begin();
		Produto produto = daoproduto.read(nome);
		Tipo tipo = daotipo.read(nometipo);
		if(produto != null) {
			DAO.rollback();
			throw new Exception("cadastrar produto - produto já cadastrado:" + nome);
		}
		
		if(tipo == null) {
			DAO.rollback();
			throw new Exception("cadastrar produto - tipo não cadastrado:" + nometipo);
		}
		
		produto = new Produto(nome, preco, tipo);
		tipo.adicionar(produto);
		daoproduto.create(produto);
		daotipo.update(tipo);
		DAO.commit();
		return produto;
	}
	
	public static Tipo cadastrarTipo(String nome) throws Exception {
		DAO.begin();
		Tipo tipo = daotipo.read(nome);
		if(tipo != null) {
			DAO.rollback();
			throw new Exception("cadastrar tipo - tipo já cadastrado:" + nome);
		}
		
		tipo = new Tipo(nome);
		daotipo.create(tipo);
		DAO.commit();
		return tipo;
	}
	
	public static void adicionarProduto(String nomecliente, String nomeproduto) throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		Produto produto = daoproduto.read(nomeproduto);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("adicionar produto - cliente não cadastrado:" + nomecliente);
		}
		
		if(produto == null) {
			DAO.rollback();
			throw new Exception("adicionar produto - produto não cadastrado:" + nomeproduto);
		}
		
		Conta conta = cliente.ultimaContaAtiva();
		if(conta == null) {
			DAO.rollback();
			throw new Exception("adicionar produto - cliente sem conta ativa:" + nomecliente);
		}
		
		conta.adicionar(produto);
		produto.adicionar(conta);
		daoconta.update(conta);
		daoproduto.update(produto);
		DAO.commit();
	}
	
	public static void removerProduto(String nomecliente, String nomeproduto) throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		Produto produto = daoproduto.read(nomeproduto);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("remover produto - cliente não cadastrado:" + nomecliente);
		}
		
		if(produto == null) {
			DAO.rollback();
			throw new Exception("remover produto - produto não cadastrado:" + nomeproduto);
		}
		
		Conta conta = cliente.ultimaContaAtiva();
		if(conta == null) {
			DAO.rollback();
			throw new Exception("remover produto - cliente sem conta ativa:" + nomecliente);
		}
		
		List<Produto> produtos = conta.getProdutos();
		if(!produtos.contains(produto)) {
			DAO.rollback();
			throw new Exception("remover produto - cliente não adicionou esse produto:" + nomeproduto);
		}
		
		conta.remover(produto);
		produto.remover(conta);
		daoconta.update(conta);
		daoproduto.update(produto);
		DAO.commit();
	}
	
	public static Conta fecharConta(String nomecliente) throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("fechar conta - cliente não cadastrado:" + nomecliente);
		}
		
		Conta conta = cliente.ultimaContaAtiva();
		if(conta == null) {
			DAO.rollback();
			throw new Exception("fechar conta - cliente sem conta ativa:" + nomecliente);
		}
		
		conta.setAtivo(false);
		daoconta.update(conta);
		DAO.commit();
		return conta;
	}
	
	public static void excluirCliente(String nomecliente) throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("excluir cliente - cliente não cadastrado:" + nomecliente);
		}
		
		
		List<Conta> contas = cliente.getContas();
		for(int i = 0; i < contas.size(); i++) {
			Conta conta = contas.get(i);
			List<Produto> produtos = conta.getProdutos();
			for(int j = 0; j < produtos.size(); j++) {
				Produto produto = produtos.get(j);
				produto.remover(conta);
				daoproduto.update(produto);
			}
			daoconta.delete(conta);
		}
		
		daocliente.delete(cliente);
		DAO.commit();
	}
	
	public static void excluirProduto(String nomeproduto) throws Exception {
		DAO.begin();
		Produto produto = daoproduto.read(nomeproduto);
		if(produto == null) {
			DAO.rollback();
			throw new Exception("excluir produto - produto não cadastrado:" + nomeproduto);
		}
		
		List<Conta> contas = produto.getContas();
		for(int i = 0; i < contas.size(); i++) {
			Conta conta = contas.get(i);
			conta.remover(produto);
			daoconta.update(conta);
		}
		
		Tipo tipo = produto.getTipo();
		if(tipo != null) {
			tipo.remover(produto);
			daotipo.update(tipo);
		}
		
		daoproduto.delete(produto);
		DAO.commit();
	}
	
	public static void excluirTipo(String nometipo) throws Exception {
		DAO.begin();
		Tipo tipo = daotipo.read(nometipo);
		if(tipo == null) {
			DAO.rollback();
			throw new Exception("excluir tipo - tipo não cadastrado:" + nometipo);
		}
		
		for(Produto produto : tipo.getProdutos()) {
			produto.setTipo(null);
			daoproduto.update(produto);
		}
		
		daotipo.delete(tipo);
		DAO.commit();
	}
	
	public static void alterarCliente(String nomecliente, String novonome) throws Exception {
		DAO.begin();
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null) {
			DAO.rollback();
			throw new Exception("alterar nome do cliente - cliente não cadastrado:" + nomecliente);
		}
		
		cliente.setNome(novonome);
		daocliente.update(cliente);
		DAO.commit();
	}
	
	public static void alterarProduto(String nomeproduto, String novonome) throws Exception {
		DAO.begin();
		Produto produto = daoproduto.read(nomeproduto);
		if(produto == null) {
			DAO.rollback();
			throw new Exception("alterar produto - produto não cadastrado:" + nomeproduto);
		}
		
		produto.setNome(novonome);
		daoproduto.update(produto);
		DAO.commit();
	}
	
	public static void alterarProdutoTipo(String nomeproduto, String novotipo) throws Exception {
		DAO.begin();
		Produto produto = daoproduto.read(nomeproduto);
		Tipo tipo = daotipo.read(novotipo);
		if(produto == null) {
			DAO.rollback();
			throw new Exception("alterar tipo do produto - produto não cadastrado:" + nomeproduto);
		}
		
		if(tipo == null) {
			DAO.rollback();
			throw new Exception("alterar tipo do produto - tipo não cadastrado:" + novotipo);
		}
		
		Tipo t = produto.getTipo();
		if(t != null) {
			t.remover(produto);
			daotipo.update(t);	
		}
		
		produto.setTipo(tipo);
		tipo.adicionar(produto);
		daoproduto.update(produto);
		daotipo.update(tipo);
		DAO.commit();
	}
	
	public static void alterarTipo(String nometipo, String novonome) throws Exception {
		DAO.begin();
		Tipo tipo = daotipo.read(nometipo);
		if(tipo == null) {
			DAO.rollback();
			throw new Exception("alterar tipo - tipo não cadastrado:" + nometipo);
		}
		
		tipo.setNome(novonome);
		daotipo.update(tipo);
		DAO.commit();
	}
	
	public static String listarClientes() {
		List<Cliente> clientes = daocliente.readAll();
		String texto = "-----------listagem de Clientes-----------\n";
		for(Cliente c : clientes)
			texto += c + "\n";
		return texto;
	}
	
	public static String listarContas() {
		List<Conta> contas = daoconta.readAll();
		String texto = "-----------listagem de Contas-----------\n";
		for(Conta c : contas)
			texto += c + "\n";
		return texto;
	}
	
	public static String listarContasPorCliente(String nomecliente) throws Exception {
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null)
			throw new Exception("listar contas de cliente - cliente não cadastrado:" + nomecliente);
		
		List<Conta> contas = cliente.getContas();
		String texto = "-----------listagem de contas do cliente " + nomecliente + "-----------\n";
		for(Conta c : contas)
			texto += c + "\n";
		return texto;
	}
	
	public static String listarProdutos() {
		List<Produto> produtos = daoproduto.readAll();
		String texto = "-----------listagem de Produtos-----------\n";
		for(Produto p : produtos)
			texto += p + "\n";
		return texto;
	}
	
	public static String listarTipos() {
		List<Tipo> tipos = daotipo.readAll();
		String texto = "-----------listagem de Tipos-----------\n";
		for(Tipo t : tipos)
			texto += t + "\n";
		return texto;
	}
	
	public static String consultaClientesPorParteNome(String caracteres) {
		List<Cliente> result = daocliente.consultaClientesPorParteNome(caracteres);
		String texto = "";
		if (result.isEmpty())  
			texto += "consulta vazia";
		else 
			for(Cliente x: result)
				texto +=  x + "\n";
		return texto;
	}
	
	public static String consultaClientesNContas(int n){
		List<Cliente> result = daocliente.consultaClientesNContas(n);
		String texto = "";
		if (result.isEmpty())  
			texto += "consulta vazia";
		else 
			for(Cliente x: result)
				texto +=  x + "\n";
		return texto;
	}
	
	public static String consultaClientesPorTipo(String tipo){
		List<Cliente> result = daocliente.consultaClientesPorTipo(tipo);
		String texto = "";
		if (result.isEmpty())  
			texto += "consulta vazia";
		else 
			for(Cliente x: result)
				texto +=  x + "\n";
		return texto;
	}
	
	public static String consultaClientesNProdutos(int n){
		List<Cliente> result = daocliente.consultaClientesNProdutos(n);
		String texto = "";
		if (result.isEmpty())  
			texto += "consulta vazia";
		else 
			for(Cliente x: result)
				texto +=  x + "\n";
		return texto;
	}
	
	public static String consultaProdutoPorTipo(String tipo){
		List<Produto> result = daoproduto.consultaProdutoPorTipo(tipo);
		String texto = "";
		if (result.isEmpty())  
			texto += "consulta vazia";
		else 
			for(Produto x: result)
				texto +=  x + "\n";
		return texto;
	}
	
	public static List<Produto> getProdutos() {
		return daoproduto.readAll();
	}
	
	public static List<Tipo> getTipos() {
		return daotipo.readAll();
	}
	
	public static List<Produto> produtosConta(String nomecliente) throws Exception {
		Cliente cliente = daocliente.read(nomecliente);
		if(cliente == null)
			throw new Exception("cliente não cadastrado");
		
		Conta conta = cliente.ultimaContaAtiva();
		if(conta == null)
			throw new Exception("cliente sem conta ativa");
		
		return conta.getProdutos();
	}
}
