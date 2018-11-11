package com.datainfo.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.filter.LoggingFilter;

import com.datainfo.rest.entities.UsuarioExterno;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

public class TestRestAPI {

	public static String WS_PATH = "http://localhost:8080/java-rest-crud/services/";
//	public static String WS_PATH = "http://localhost:8080/services/";

	public static void main(String[] args) {

		UsuarioExterno user = new UsuarioExterno();
		user.setNu_cpf("04787784933");
		user.setNo_usuario("Rafael");
		user.setDe_email("rafael.rr@gmail.com");
		user.setIc_situacao('I');
		user.setIc_perfil_acesso(2);
		user.setCo_funcao(1);
		user.setNu_telefone("47988776633");

		post(user);

		user.setDe_email("maria@gmail.com");
		user.setIc_situacao('A');

		put(user);

		get();

		patch("04787784933", "I");

		delete("04787784933");
	}

	private static void post(UsuarioExterno user) {
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class).register(GensonJsonConverter.class));
		WebTarget webTarget = client.target(WS_PATH).path("usuarios");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.json(user));

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	private static void get() {
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(WS_PATH).path("usuarios") //
				.queryParam("nome=R").queryParam("situacao=A").queryParam("pagina=0").queryParam("limite=2");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get(Response.class);

		String usuarios = response.readEntity(String.class);

		System.out.println(response.getStatus());
		System.out.println(usuarios);
	}

	private static void delete(String id) {
		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(WS_PATH).path("usuarios").path(id);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.delete();

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	private static void patch(String id, String situacao) {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFilter.class);
		config.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

		Client client = ClientBuilder.newClient(config);
		WebTarget webTarget = client.target(WS_PATH).path("usuarios").path(id).queryParam("ic_situacao", situacao);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.method("PATCH");

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	private static void put(UsuarioExterno user) {
		ClientConfig config = new ClientConfig();
		config.register(LoggingFilter.class);
		config.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);

		Client client = ClientBuilder.newClient(config);
		WebTarget webTarget = client.target(WS_PATH).path("usuarios");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.json(user));

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}
