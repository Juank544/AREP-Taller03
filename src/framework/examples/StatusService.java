package framework.examples;

import framework.Service;

public class StatusService {

    @Service("/status")
    public static String status(){
        return "HTTP/1.1 200 OK\r\n"
                +"Content-Type: text/html\r\n"
                +"\r\n"
                +"<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "status ok"
                + "</body>"
                + "</html>";
    }
}
