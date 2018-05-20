package com.laszlojeszmas.webroute;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

abstract class ServerHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException{
        System.out.println(t.getRequestURI().toString());
        String response = getAnnotation(t.getRequestURI().toString());
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String getAnnotation(String route) {
        for (Method method : getRouteMethods()) {
            Annotation annotation = method.getAnnotation(WebRoute.class);
            WebRoute webroute = (WebRoute) annotation;
            if (webroute.value().equals(route)){
                try {
                Object string = method.invoke(RouteResponse.mainPage());
                    return (String) string;
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
        return null;
    }





    public Method[] getRouteMethods(){
        return RouteResponse.class.getMethods();
    }
}
