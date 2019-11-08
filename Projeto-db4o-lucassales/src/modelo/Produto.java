package modelo;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private String nome;
	private double preco;
	//private ArquivoImagem imagem;
	private Tipo tipo = null;
	private List<Conta> contas = new ArrayList<>();
	
	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
		//this.imagem = imagem;
	}
	
	public Produto(String nome, double preco, Tipo tipo) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
		//this.imagem = imagem;
	}
	
	public void adicionar(Conta c) {
		contas.add(c);
	}
	
	public void remover(Conta c) {
		contas.remove(c);
	}
	
	public Conta localizarConta(int id) {
		for(Conta c : contas) {
			if(c.getId() == id)
				return c;
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	/*public ArquivoImagem getImagem() {
		return imagem;
	}

	public void setImagem(ArquivoImagem imagem) {
		this.imagem = imagem;
	}*/
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", preco=" + preco + "]";
	}
}
