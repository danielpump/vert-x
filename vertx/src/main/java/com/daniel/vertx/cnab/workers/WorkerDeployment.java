/**
 * 
 */
package com.daniel.vertx.cnab.workers;

import io.vertx.core.DeploymentOptions;

/**
 * Interface para definir as configurações de depoly de um worker
 * @author daniel
 *
 */
public interface WorkerDeployment {
	
	public DeploymentOptions configuracoesDeDeploy();
	

}
