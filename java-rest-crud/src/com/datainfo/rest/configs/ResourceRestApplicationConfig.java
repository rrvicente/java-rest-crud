package com.datainfo.rest.configs;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

/**
 * A classe de aplicativo JAX-RS permite, entre outras coisas, customizar quais
 * pacotes são verificados para provedores de serviço.
 */
public class ResourceRestApplicationConfig extends ResourceConfig {
	public ResourceRestApplicationConfig() {
		// pacotes disponibilizados como provedores de serviços
		packages("com.datainfo.rest.services");

		// registro de logs de acesso aos serviços disponibilizados
		register(LoggingFilter.class);

		// provedor de vinculação JSON
		register(GensonJsonConverter.class);
	}
}