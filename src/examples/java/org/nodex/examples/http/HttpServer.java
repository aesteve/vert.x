package org.nodex.examples.http;

import org.nodex.core.Callback;
import org.nodex.core.http.HttpConnection;
import org.nodex.core.http.HttpCallback;
import org.nodex.core.http.HttpRequest;
import org.nodex.core.http.HttpResponse;

import java.util.Map;

/**
 * User: tfox
 * Date: 04/07/11
 * Time: 17:29
 */
public class HttpServer {
  public static void main(String[] args) throws Exception {
    org.nodex.core.http.HttpServer server = org.nodex.core.http.HttpServer.createServer(new Callback<HttpConnection>() {
      public void onEvent(final HttpConnection conn) {
        conn.request(new HttpCallback() {
          public void onRequest(HttpRequest req, HttpResponse resp) {
            System.out.println("Got request " + req.uri);
            System.out.println("Headers are: ");
            for (Map.Entry<String, String> headers : req.headers.entrySet()) {
              System.out.println(headers.getKey() + ":" + headers.getValue());
            }
            resp.headers.put("Content-Type", "text/html; charset=UTF-8");
            resp.write("<html><body><h1>Hello from node.x!</h1></body></html>", "UTF-8").end();
          }
        });
      }
    }).listen(8080);

    System.out.println("Any key to exit");
    System.in.read();

    server.stop();
  }
}
