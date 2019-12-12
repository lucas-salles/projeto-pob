/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Conta;

public class DAOCliente  extends DAO<Cliente>{
	public Cliente read (Object chave) {
		String nom = (String) chave;
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(nom);
		List<Cliente> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public Cliente readByCpf (Object chave) {
		String cpf = (String) chave;
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("cpf").constrain(cpf);
		List<Cliente> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE CLIENTE
	 * 
	 **********************************************************/
	
	public  List<Cliente> consultaClientesPorParteNome(String caracteres) {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("nome").constrain(caracteres).like();
		List<Cliente> result = q.execute(); 
		return result;
	}
	
	public List<Cliente> consultaClientesNContas(int n) {
		Query q = manager.query();
		q.constrain(Cliente.class);  		
		q.constrain(new Filtro(n));
		List<Cliente> resultados = q.execute();
		return resultados;
	}
	
	public List<Cliente> consultaClientesPorTipo(String tipo) {
		Query q = manager.query();
		q.constrain(Cliente.class);
		q.descend("contas").descend("produtos").descend("tipo").descend(tipo);
		List<Cliente> resultados = q.execute();
		return resultados;
	}
	
	public List<Cliente> consultaClientesNProdutos(int n) {
		Query q = manager.query();
		q.constrain(Cliente.class);  		
		q.constrain(new Filtro1(n));
		List<Cliente> resultados = q.execute();
		return resultados;
	}
	
	//====================================================================
	@SuppressWarnings("serial")
	class Filtro implements Evaluation {
		private int n;
		public Filtro (int n) {this.n=n;}
		public void evaluate(Candidate candidate) {
			Cliente c = (Cliente) candidate.getObject();
			boolean filtro = c.getContas().size()==n;
			candidate.include(filtro);
		}
	}
	
	@SuppressWarnings("serial")
	class Filtro1 implements Evaluation {
		private int n;
		public Filtro1 (int n) {this.n=n;}
		public void evaluate(Candidate candidate) {
			Cliente c = (Cliente) candidate.getObject();
			boolean filtro = false;
			for(Conta conta : c.getContas()) {
				filtro = conta.getProdutos().size()>=n;
			}
			candidate.include(filtro);
		}
	}
}