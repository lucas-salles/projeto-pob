/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Cliente;
import modelo.Produto;

public class DAOCliente extends DAO<Cliente>{
	
	public Cliente read(Object chave){
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("select p from Cliente p where p.nome= '" + nome +"'");
			return (Cliente) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	public Cliente readByCpf (Object chave) {
		try {
			String cpf = (String) chave;
			Query q = manager.createQuery("select p from Cliente p where p.cpf= '" + cpf +"'");
			return (Cliente) q.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE CLIENTE
	 * 
	 **********************************************************/
	
	public  List<Cliente> consultaClientesPorParteNome(String caracteres) {
		Query q = manager.createQuery
				("select c from Cliente c where c.nome like '%"+caracteres+"%' ");
		return (List<Cliente>) q.getResultList();
	}
	
	public List<Cliente> consultaClientesNContas(int n) {
		Query q = manager.createQuery("select c from Cliente c where SIZE(c.contas)= :x");
		q.setParameter("x", n);
		return (List<Cliente>) q.getResultList();
	}
	
	public List<Cliente> consultaClientesPorTipo(String tipo) {
		try{
			Query q = manager.createQuery("select cl from Cliente cl join cl.contas co join co.produtos p where p.tipo.nome= :x");
			q.setParameter("x", tipo);
			return (List<Cliente>) q.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Cliente> consultaClientesNProdutos(int n) {
		try{
			Query q = manager.createQuery("select cl from Cliente cl join cl.contas co where SIZE(co.produtos)>= :x");
			q.setParameter("x", n);
			return (List<Cliente>) q.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}
}

