/**
 * 
 */
package com.daniel.vertx.cnab.conversores;

import io.vertx.core.json.JsonObject;

/**
 * Interface padrao de conversão de objetos do body.
 * @author daniel
 *
 */
@FunctionalInterface
public interface Conversor<T> {

	T converter(JsonObject jsonBody);
	
}
