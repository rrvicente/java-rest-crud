package com.datainfo.rest.entities.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datainfo.datasource.ConnectionDB;
import com.datainfo.datasource.QueryExecutor;
import com.datainfo.rest.entities.UsuarioExterno;

public class UsuarioExternoDAOImpl implements UsuarioExternoDAO {

	@Override
	public void create(UsuarioExterno user) throws SQLException {
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO USUARIO_EXTERNO(nu_cpf, no_usuario, de_email, ic_situacao, ic_perfil_acesso, co_funcao, nu_telefone) VALUES(");
		str.append("'").append(user.getNu_cpf()).append("',");
		str.append("'").append(user.getNo_usuario()).append("',");
		str.append("'").append(user.getDe_email()).append("',");
		str.append("'").append(user.getIc_situacao()).append("',");
		str.append(user.getIc_perfil_acesso()).append(",");
		str.append(user.getCo_funcao()).append(",");
		str.append("'").append(user.getNu_telefone()).append("')");

		new QueryExecutor(str.toString(), new ConnectionDB().getConn());
	}

	@Override
	public void update(UsuarioExterno user) throws SQLException {
		StringBuilder str = new StringBuilder();
		str.append("UPDATE USUARIO_EXTERNO SET");
		str.append(" nu_cpf ='").append(user.getNu_cpf()).append("'");
		str.append(" ,no_usuario ='").append(user.getNo_usuario()).append("'");
		str.append(" ,de_email ='").append(user.getDe_email()).append("'");
		str.append(" ,ic_situacao ='").append(user.getIc_situacao()).append("'");
		str.append(" ,ic_perfil_acesso =").append(user.getIc_perfil_acesso());
		str.append(" ,co_funcao =").append(user.getCo_funcao());
		str.append(" ,nu_telefone ='").append(user.getNu_telefone()).append("'");
		str.append(" WHERE ");
		str.append(" nu_cpf ='").append(user.getNu_cpf()).append("'");

		new QueryExecutor(str.toString(), new ConnectionDB().getConn());
	}

	@Override
	public void delete(String id) throws SQLException {
		new QueryExecutor("DELETE FROM USUARIO_EXTERNO WHERE NU_CPF =" + id, new ConnectionDB().getConn());
	}

	@Override
	public void enable(String id, String ic_situacao) throws SQLException {
		StringBuilder str = new StringBuilder();
		str.append("UPDATE USUARIO_EXTERNO SET");
		str.append(" ic_situacao ='").append(ic_situacao).append("'");
		str.append(" WHERE ");
		str.append(" nu_cpf ='").append(id).append("'");

		new QueryExecutor(str.toString(), new ConnectionDB().getConn());
	}

	@Override
	public List<UsuarioExterno> list(String nome, String situacao, String perfil, String pagina, String limite) throws SQLException {
		List<UsuarioExterno> list = new ArrayList<UsuarioExterno>();

		String filter = "";
		if (nome != null && !nome.isEmpty())
			filter += " NO_USUARIO LIKE '%" + nome + "%'";

		if (situacao != null && !situacao.isEmpty()) {
			filter += filter.isEmpty() ? "" : " AND ";
			filter += "IC_SITUACAO = '" + situacao + "'";
		}

		if (perfil != null && !perfil.isEmpty()) {
			filter += filter.isEmpty() ? "" : " AND ";
			filter += "IC_PERFIL_ACESSO = " + perfil;
		}

		String pagination = "";
		if (pagina != null && !pagina.isEmpty() && limite != null && !limite.isEmpty())
			pagination = " LIMIT " + pagina + "," + limite;

		QueryExecutor executor = new QueryExecutor("SELECT * FROM USUARIO_EXTERNO" + (filter.isEmpty() ? "" : " WHERE " + filter) + pagination, new ConnectionDB().getConn());

		while (executor.eof()) {

			UsuarioExterno user = new UsuarioExterno();
			user.setNu_cpf((String) executor.fieldget("NU_CPF"));
			user.setNo_usuario((String) executor.fieldget("NO_USUARIO"));
			user.setDe_email((String) executor.fieldget("DE_EMAIL"));
			user.setIc_situacao(String.valueOf(executor.fieldget("IC_SITUACAO")).charAt(0));
			user.setIc_perfil_acesso((int) executor.fieldget("IC_PERFIL_ACESSO"));
			user.setCo_funcao((int) executor.fieldget("CO_FUNCAO"));
			user.setNu_telefone((String) executor.fieldget("NU_TELEFONE"));

			list.add(user);

			executor.skip(1);
		}

		return list;
	}

}
