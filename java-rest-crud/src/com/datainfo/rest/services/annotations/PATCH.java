package com.datainfo.rest.services.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.HttpMethod;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
public @interface PATCH {
//	Por padr�o o Jersey n�o possui uma implementa��o para a anota��o PATCH (updates parciais).
//	Por este motivo se faz necess�rio cri�-lo aqui.
}
