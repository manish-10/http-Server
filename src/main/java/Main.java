import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");
    // Uncomment this block to pass the first stage
    //
     try {
           ServerSocket serverSocket = new ServerSocket(4221);

           // Since the tester restarts your program quite often, setting SO_REUSEADDR
           // ensures that we don't run into 'Address already in use' errors
           serverSocket.setReuseAddress(true);

           while (true) {
               Socket clientSocket = serverSocket.accept(); // Wait for connection from client.
                new Thread(() -> handleClient(clientSocket)).start();
           }
         } catch (IOException e) {
           System.out.println("IOException: " + e.getMessage());
         }
  }
  private static void handleClient(Socket clientSocket) {
      RequestHandler req;
      ResponseHandler res;
    try{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String requestMessage;

        // read req and validate path
        req = new RequestHandler(in);
        boolean isUrlPathValid = req.validateURLPath();
        String pathVariable = req.getPathVariable();
        String userAgentHeaderContent = req.getHeaderContent("user-agent");

        //send response message
        res = new ResponseHandler();
        res.sendResponse(out, isUrlPathValid, pathVariable, userAgentHeaderContent);

        System.out.println("accepted new connection");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
}
