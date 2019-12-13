package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;


@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Conta  {
	@Id
	@GeneratedValue
	@Field(name="_id")		//chave
	private String id;	//autoincrementado
	private LocalDateTime data = LocalDateTime.now();
	private double total = 0;
	private boolean ativo = true;
	@ManyToOne
	private Cliente cliente;
	@ManyToMany(mappedBy="contas")
	private List<Produto> produtos = new ArrayList<>();
	
	public Conta() {}
	
	public Conta(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionar(Produto p) {
		this.total += p.getPreco();
		produtos.add(p);
	}
	
	public void remover(Produto p) {
		if(produtos.contains(p))
			this.total -= p.getPreco();
		produtos.remove(p);
	}
	
	public Produto localizarProduto(String nome) {
		for(Produto p : produtos) {
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public String getId() {
		return id;
	}
	
	public LocalDateTime getData() {
		return this.data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public boolean getAtivo() {
		return this.ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", data=" + data + ", total=" + total + ", ativo=" + ativo + ", cliente=" + cliente.getNome()
				+ ", produtos=" + produtos.size() + "]";
	}
}
