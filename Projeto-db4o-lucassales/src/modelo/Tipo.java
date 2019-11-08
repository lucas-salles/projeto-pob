package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tipo {
	private String nome;
	private List<Produto> produtos = new ArrayList<>();
	
	public Tipo(String nome) {
		this.nome = nome;
	}
	
	public void adicionar(Produto p) {
		produtos.add(p);
	}
	
	public void remover(Produto p) {
		produtos.remove(p);
	}
	
	public Produto localizarProduto(String nome) {
		for(Produto p : produtos) {
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Tipo [nome=" + nome + "]";
	}
}
