/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Tipo;

public class DAOTipo extends DAO<Tipo>{
	
	public Tipo read(Object chave){
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("select p from Tipo p where p.nome= '" + nome +"'");
			return (Tipo) q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
}

