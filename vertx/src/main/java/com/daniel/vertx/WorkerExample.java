/**
 * 
 */
package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daniel
 *
 */
public class WorkerExample extends AbstractVerticle {

	final AtomicBoolean canSend = new AtomicBoolean(true);

	@Override
	public void start() throws Exception {
		final EventBus eventBus = vertx.eventBus();

		Handler<Message<JsonObject>> resultHandler = new Handler<Message<JsonObject>>() {

			@Override
			public void handle(Message<JsonObject> message) {
				canSend.set(true);
				System.out.println(Thread.currentThread().getName() + "=>Fib("
						+ message.body().getString("number") + ","
						+ message.body().getString("result") + ")");
				eventBus.send("log.consumer", "Logging output "
						+ message.body().getString("result"));
			}
		};
		eventBus.consumer("fib.response", resultHandler);

		vertx.setPeriodic(100, new Handler<Long>() {
			public void handle(final Long timerID) {
				System.out.println(Thread.currentThread().getName()
						+ "=>Sending event");
				if (canSend.get()) {
					eventBus.send("fib.request", 7);
					canSend.set(false);
				}
			}
		});

		vertx.deployVerticle("com.daniel.vertx.FibonnaciWorker",
				new DeploymentOptions().setInstances(10).setWorker(true));
		//vertx.deployVerticle("logconsumer.js");
		System.out.println("Iniciando requisicao");

	}

}
