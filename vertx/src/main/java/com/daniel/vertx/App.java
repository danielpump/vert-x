package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		vertx.createHttpServer()
				.requestHandler(
						r -> {
							r.response().end(
									"<h1>Hello from my first "
											+ "Vert.x 3 application</h1>");
						}).listen(8080, result -> {
					if (result.succeeded()) {
						startFuture.complete();
					} else {
						startFuture.fail(result.cause());
					}
				});
	}

}
