package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Tipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Field(name="_id")		//chave
	private String id;
	private String nome;
	@OneToMany(mappedBy="tipo")
	private List<Produto> produtos = new ArrayList<>();
	
	public Tipo() {}
	
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
	
	public int getId() {
		return id;
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
