/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Conta;

public class DAOConta  extends DAO<Conta>{
	public Conta read (Object chave) {
		int id = (Integer) chave;
		Query q = manager.query();
		q.constrain(Conta.class);
		q.descend("id").constrain(id);
		List<Conta> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}