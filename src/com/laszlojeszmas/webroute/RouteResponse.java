package com.laszlojeszmas.webroute;

public class RouteResponse {

    @WebRoute(route = "/test")
    public static String mainPage(){
        return "This is the mainpage!";
    }

    @WebRoute(route = "/test1")
    public static String testPage(){
        return "This is the second test page!";
    }

    @WebRoute(route = "/user/<username>")
    public static String test2Page(String username){
        return username;
    }
}
