/**
 * 
 */
package com.daniel.vertx.mongo.entidades;

import io.vertx.core.json.JsonObject;

/**
 * @author daniel
 *
 */
public interface EntidadeMongo {
	
	String get_id();
	
	void set_id(String _id);
	
	JsonObject toJSON();

}
