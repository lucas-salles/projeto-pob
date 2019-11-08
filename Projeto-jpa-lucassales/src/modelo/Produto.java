package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double preco;
	@ManyToOne
	private Tipo tipo = null;
	@ManyToMany
	private List<Conta> contas = new ArrayList<>();
	
	public Produto() {}
	
	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto(String nome, double preco, Tipo tipo) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
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
	
	public int getId() {
		return id;
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
