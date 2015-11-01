/**
 * 
 */
package com.daniel.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.shareddata.LocalMap;

/**
 * @author daniel
 *
 */
public class ChatWebSocketServer extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		HttpServer server = vertx.createHttpServer();
		server.requestHandler(new Handler<HttpServerRequest>() {

			@Override
			public void handle(HttpServerRequest request) {
				request.response()
						.sendFile(
								'.' + (request.path().equals("/") ? "/resource/index.html"
										: request.path()));
			}

		});

		server.websocketHandler(new Handler<ServerWebSocket>() {

			@Override
			public void handle(final ServerWebSocket ws) {
				if (!ws.path().equals("/chat")) {
					ws.reject();
					return;
				}
				final LocalMap<Object, String> wsSessions = vertx.sharedData()
						.getLocalMap("websocket.chat.sessions");
				if (wsSessions == null) {
					System.out.println("Deu erro!");
				}
				wsSessions.put(ws.textHandlerID(), ws.textHandlerID());

				ws.handler(new Handler<Buffer>() {

					@Override
					public void handle(Buffer buffer) {
						for (String handlerId : wsSessions.values()) {
							System.err.println("Sending " + handlerId);
							vertx.eventBus().send(handlerId, buffer.toString());
						}
					}

				});

				ws.closeHandler(new Handler<Void>() {

					@Override
					public void handle(Void v) {
						System.err.println("Socket closed. Removing"
								+ ws.binaryHandlerID());
						wsSessions.remove(ws.textHandlerID());
					}

				});
			}

		});

		server.listen(8080);

	}

}
