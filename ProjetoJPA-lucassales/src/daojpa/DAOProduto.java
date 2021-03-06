/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Produto;

public class DAOProduto extends DAO<Produto>{

	public Produto read (Object chave){
		try{
			String nome = (String) chave;
			Query q = manager.createQuery("select p from Produto p where p.nome= '" + nome +"'");
			return (Produto) q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<Produto> consultaProdutoPorTipo(String tipo) {
		try{
			Query q = manager.createQuery("select p from Produto p where p.tipo.nome= :x");
			q.setParameter("x", tipo);
			return (List<Produto>) q.getResultList();
		}catch(NoResultException e){
			return null;
		}
	}
}

