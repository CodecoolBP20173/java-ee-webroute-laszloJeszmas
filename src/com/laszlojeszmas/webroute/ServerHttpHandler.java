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
        String requestMethod = t.getRequestMethod().toString();
        String uri = t.getRequestURI().toString();
        String response = getAnnotation(uri, requestMethod);
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public String getAnnotation(String route, String requestMethod) {

        for (Method method : getRouteMethods()) {
            Annotation annotation = method.getAnnotation(WebRoute.class);
            WebRoute webroute = (WebRoute) annotation;
            try {
                if (webroute.route().equals(route) && webroute.method().equals(requestMethod)) {
                    Object string = method.invoke(method);
                    return (String) string;
                } else if (route.contains("user/")) {
                    Class aClass = RouteResponse.class;
                    Method method1 = aClass.getMethod("test2Page", String.class);
                    Object string = method.invoke(method1, getName(route));
                    return (String) string;
                }
            } catch (Exception e){
                System.out.println(e);
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

    public String getName(String string){
        return string.substring(string.lastIndexOf("/") + 1);
    }
}
