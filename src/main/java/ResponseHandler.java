import java.io.PrintWriter;

public class ResponseHandler {
    private String responseMessage;
    void sendResponse(PrintWriter resWriter, boolean isPathValid, String pathVariable){
        String httpStatusOk = "HTTP/1.1 200 OK\r\n\r\n";
        String httpStatusNotFound= "HTTP/1.1 404 Not Found\r\n\r\n";
        int pathVariableLength = pathVariable != null ? pathVariable.length(): 0;
        StringBuilder sb = new StringBuilder();
        if(pathVariableLength != 0)
        {
            sb.append("HTTP/1.1 200 OK\r\n")
                    .append("Content-Type: text/plain\r\n")
                    .append("Content-Length: ").append(pathVariableLength).append("\r\n")
                    .append("\r\n")
                    .append(pathVariable);
        }
        String httpResponseWithHeaderAndBody = sb.toString();
        if(isPathValid && pathVariableLength != 0)
            resWriter.println(httpResponseWithHeaderAndBody);
        else if(isPathValid)
            resWriter.println(httpStatusOk);
        else
            resWriter.println(httpStatusNotFound);
    }
}
