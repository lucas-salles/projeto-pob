/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Cliente;

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
}

