import java.io.PrintWriter;

public class ResponseHandler {
    private String responseMessage;
    void sendResponse(PrintWriter resWritter, boolean isPathValid){
        String statusOk = "HTTP/1.1 200 OK\r\n\r\n";
        String statusNotFound= "HTTP/1.1 404 Not Found\r\n\r\n";
        if(isPathValid)
            resWritter.println(statusOk);
        else
            resWritter.println(statusNotFound);
    }
}
