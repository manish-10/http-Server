public class RequestHandler {
    private String requestMessage;
    boolean validateURLPath(String requestMessage){
        String[] req = requestMessage.split(" ");

        return req[1].equals("/index.html") || req[1].equals("/");
    }
}
