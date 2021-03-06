package com.laszlojeszmas.webroute;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class WebServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new ServerHttpHandler() { // Ask mentor what is exactly S is;
            @Override
            public void handle(HttpExchange t) throws IOException {
                super.handle(t);
            }
        });
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
