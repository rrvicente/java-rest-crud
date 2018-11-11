package com.datainfo.rest.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.datainfo.rest.entities.UsuarioExterno;
import com.datainfo.rest.entities.dao.UsuarioExternoDAOImpl;
import com.datainfo.rest.services.annotations.PATCH;

@Path("usuarios")
public class UsuarioExternoServices {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam("nome") String nome, @QueryParam("situacao") String situacao, @QueryParam("perfil") String perfil, @QueryParam("pagina") String pagina, @QueryParam("limite") String limite) {

		try {

			UsuarioExternoDAOImpl dao = new UsuarioExternoDAOImpl();

			return Response.status(200).entity(dao.list(nome, situacao, perfil, pagina, limite)).build();

		} catch (SQLException e) {
			e.printStackTrace();

			return Response.status(400).entity("Ocorreu um erro durante a consulta de usu�rios.").build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {

		try {

			UsuarioExternoDAOImpl dao = new UsuarioExternoDAOImpl();
			dao.delete(id);

			return Response.status(202).entity("Exclus�o efetuada com sucesso.").build();

		} catch (SQLException e) {
			e.printStackTrace();

			return Response.status(400).entity("Ocorreu um erro durante o processo de exclus�o do usu�rio.").build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(UsuarioExterno user) {

		try {
			UsuarioExternoDAOImpl dao = new UsuarioExternoDAOImpl();
			dao.create(user);

			return Response.status(202).entity("Cadastro efetuado com sucesso!").build();

		} catch (SQLException e) {
			e.printStackTrace();

			if (e instanceof SQLIntegrityConstraintViolationException)
				return Response.status(400).entity("Opera��o n�o realizada. Usu�rio j� inclu�do.").build();
			else
				return Response.status(400).entity("Ocorreu um erro durante o processo de cadastro do usu�rio.").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UsuarioExterno user) {

		try {

			UsuarioExternoDAOImpl dao = new UsuarioExternoDAOImpl();
			dao.update(user);

			return Response.status(202).entity("Altera��o efetuada com sucesso!").build();

		} catch (SQLException e) {
			e.printStackTrace();

			return Response.status(400).entity("Ocorreu um erro durante o processo de atualiza��o do usu�rio.").build();
		}
	}

	@PATCH
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response enable(@PathParam("id") String id, @QueryParam("ic_situacao") String ic_situacao) {

		try {

			UsuarioExternoDAOImpl dao = new UsuarioExternoDAOImpl();
			dao.enable(id, ic_situacao);

			if (ic_situacao == "A")
				return Response.status(202).entity("Usu�rio habilitado com sucesso!").build();
			else
				return Response.status(202).entity("Usu�rio desabilitado com sucesso!").build();

		} catch (SQLException e) {
			e.printStackTrace();

			return Response.status(400).entity("Ocorreu um erro durante o processo de habilitar/desabilitar o usu�rio.").build();
		}
	}

}