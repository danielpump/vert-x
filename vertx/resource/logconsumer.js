var eb = require("vertx/event_bus");
var console = require("vertx/console");

eb.registerHandler("log.consumer", function(message) {
  console.log(java.lang.Thread.currentThread().getName() + 'Log ' + message);
});