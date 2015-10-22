/**
 * 
 */
package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author daniel
 *
 */
public class FibonnaciWorker extends AbstractVerticle {

	@Override
	public void start() {
		final EventBus eventBus = vertx.eventBus();
		System.out.println("Registrando handler para fib.request");
		eventBus.consumer("fib.request", new Handler<Message<Integer>>() {

			@Override
			public void handle(Message<Integer> message) {
				Integer result = fib(message.body().intValue());
				System.out.println("Worker processing "
						+ Thread.currentThread().getName());
				JsonObject resultMessage = new JsonObject();
				resultMessage.put("number", message.body().toString());
				resultMessage.put("result", result.toString());
				eventBus.send("fib.response", resultMessage);
			}

		});
	}

	private int fib(int number) {
		if (number == 0) {
			return 0;
		} else if (number == 1) {
			return 1;
		} else {
			return fib(number - 2) + fib(number - 1);
		}
	}

}
