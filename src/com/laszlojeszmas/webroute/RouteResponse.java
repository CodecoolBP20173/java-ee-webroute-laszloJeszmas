package com.laszlojeszmas.webroute;

public class RouteResponse {

    @WebRoute(route = "/test")
    public static String mainPage(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/test\" method=\"post\">\n" +
                "    <input type=\"submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }

    @WebRoute(route = "/test1")
    public static String testPage(){
        return "This is the second test page!";
    }

    @WebRoute(route = "/user/<username>")
    public static String testVariable(String username){
        return username;
    }

    @WebRoute(route = "/test", method = "POST")
    public static String testPost(){
        return "This is a Post Method!";
    }
}
