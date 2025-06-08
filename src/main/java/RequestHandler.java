import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestHandler {
    private final Set<String> validUrlPathList;
    private final List<String> urlPaths;
    private String Host;
    private String UserAgentHeaderContent;
    public RequestHandler(BufferedReader in) throws IOException {

        List<String> requestMessage = new ArrayList<>();
        String lineToAdd;
        while ((lineToAdd = in.readLine()) != null && !lineToAdd.isEmpty()) {
            requestMessage.add(lineToAdd);
        }
        System.out.println(requestMessage);
        validUrlPathList = Stream.of("", "index.html", "echo", "user-agent")
                                    .collect(Collectors.toSet());
        String[] req = requestMessage.getFirst().split(" ");
        urlPaths = Arrays.stream(req[1].split("/"))
                .filter(s -> !s.isEmpty())
                .toList();
        for(String line: requestMessage){
            String headerContent = line.substring(line.indexOf(':') + 1).trim();
            if(line.contains("Host"))
                Host = headerContent;
            else if(line.contains("User-Agent"))
                UserAgentHeaderContent = headerContent;
        }
    }
    boolean validateURLPath(){
        if(urlPaths.isEmpty()) return true;
        return validUrlPathList.contains(urlPaths.getFirst());
    }
    String getPathVariable(){
        return urlPaths.size() > 1 ? urlPaths.getLast(): null;
    }
    String getHeaderContent(String type){
        if(type.equals("user-agent"))
            return UserAgentHeaderContent!=null && UserAgentHeaderContent.isEmpty() ? null: UserAgentHeaderContent;
        return null;
    }
}
