package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataType="order")
public class Cliente {
	@Id
	@GeneratedValue
	@Field(name="_id")		//chave
	private String id;
	private String nome;
	private String cpf;
	@OneToMany(mappedBy="cliente")
	private List<Conta> contas = new ArrayList<>();
	
	public Cliente() {}
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public void adicionar(Conta c) {
		contas.add(c);
	}
	
	public void remover(Conta c) {
		contas.remove(c);
	}
	
	public Conta localizarConta(String id) {
		for(Conta c : contas) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
	public Conta ultimaContaAtiva() {
		if(!contas.isEmpty())
			if(contas.get(contas.size() - 1).getAtivo())
				return contas.get(contas.size() - 1);
		return null;
	}
	
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + "]";
	}
}
