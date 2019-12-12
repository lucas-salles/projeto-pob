/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Tipo;

public class DAOTipo  extends DAO<Tipo>{
	public Tipo read (Object chave) {
		String nom = (String) chave;
		Query q = manager.query();
		q.constrain(Tipo.class);
		q.descend("nome").constrain(nom);
		List<Tipo> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}