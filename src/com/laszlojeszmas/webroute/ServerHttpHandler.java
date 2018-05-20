package com.laszlojeszmas.webroute;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

abstract class ServerHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException{
        System.out.println(t.getRequestURI().toString());
        String uri = t.getRequestURI().toString();
        String response = getAnnotation(uri);
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String getAnnotation(String route) {

        for (Method method : getRouteMethods()) {
            //printMethods();
            Annotation annotation = method.getAnnotation(WebRoute.class);
            WebRoute webroute = (WebRoute) annotation;
           // System.out.println(route.contains("user/"));
            if (webroute.value().equals(route)){
                try {
                    Object string = method.invoke(method);
                    return (String) string;
                } catch (Exception e) {
                    System.out.println("Error happend");
                }
            } else if (route.contains("user/")) {
                try {
                    Class aClass = RouteResponse.class;
                    Method method1 = aClass.getMethod("test2Page", String.class);
                    Object string = method.invoke(method1, "bla");
                    System.out.println(string);
                    return (String) string;
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        return null;
    }





    public Method[] getRouteMethods(){
        return RouteResponse.class.getMethods();
    }

    public void printMethods(){
        for (Method method : getRouteMethods()){
            System.out.println(method);
        }
    }
}
