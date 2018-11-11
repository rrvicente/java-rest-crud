package com.datainfo.rest.entities.dao;

import java.sql.SQLException;
import java.util.List;

import com.datainfo.rest.entities.UsuarioExterno;

interface UsuarioExternoDAO {

	public void create(UsuarioExterno user) throws SQLException;

	public void update(UsuarioExterno user) throws SQLException;

	public void enable(String id, String ic_situacao) throws SQLException;

	public void delete(String id) throws SQLException;

	public List<UsuarioExterno> list(String nome, String situacao, String perfil, String pagina, String limite) throws SQLException;
}