/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Conta;

public class DAOConta  extends DAO<Conta>{
	public Conta read (Object chave){
		try{
			int id = (Integer) chave;
			Query q = manager.createQuery("select p from Conta p where p.id= '" + id +"'");
			return (Conta) q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}
}
