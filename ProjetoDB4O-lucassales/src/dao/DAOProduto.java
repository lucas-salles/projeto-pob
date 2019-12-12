/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Produto;

public class DAOProduto  extends DAO<Produto>{
	public Produto read (Object chave) {
		String nom = (String) chave;
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("nome").constrain(nom);
		List<Produto> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE PRODUTO
	 * 
	 **********************************************************/
	
	public List<Produto> consultaProdutoPorTipo(String tipo) {
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("tipo").descend("nome").constrain(tipo);
		List<Produto> resultados = q.execute();
		return resultados;
	}
}