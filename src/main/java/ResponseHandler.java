import java.io.PrintWriter;

public class ResponseHandler {
    private String responseMessage;
    void sendResponse(PrintWriter resWriter, boolean isPathValid, String pathVariablecontent, String headerContent){
        String httpStatusOk = "HTTP/1.1 200 OK\r\n\r\n";
        String httpStatusNotFound= "HTTP/1.1 404 Not Found\r\n\r\n";
        String content = null;
        
        if(pathVariablecontent != null)
            content = pathVariablecontent;
        else if(headerContent != null)
            content = headerContent;
            
        int contentLength = content != null ? content.length(): 0;
        StringBuilder sb = new StringBuilder();
        if(contentLength != 0)
        {
            sb.append("HTTP/1.1 200 OK\r\n")
                    .append("Content-Type: text/plain\r\n")
                    .append("Content-Length: ").append(contentLength).append("\r\n")
                    .append("\r\n")
                    .append(content);
        }
        String httpResponseWithHeaderAndBody = sb.toString();
        if(isPathValid && contentLength != 0)
            resWriter.println(httpResponseWithHeaderAndBody);
        else if(isPathValid)
            resWriter.println(httpStatusOk);
        else
            resWriter.println(httpStatusNotFound);
    }
}
