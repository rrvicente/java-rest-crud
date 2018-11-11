package com.datainfo.rest.configs;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

/**
 * A classe de aplicativo JAX-RS permite, entre outras coisas, customizar quais
 * pacotes s�o verificados para provedores de servi�o.
 */
public class ResourceRestApplicationConfig extends ResourceConfig {
	public ResourceRestApplicationConfig() {
		// pacotes disponibilizados como provedores de servi�os
		packages("com.datainfo.rest.services");

		// registro de logs de acesso aos servi�os disponibilizados
		register(LoggingFilter.class);

		// provedor de vincula��o JSON
		register(GensonJsonConverter.class);
	}
}