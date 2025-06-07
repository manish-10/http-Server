import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestHandler {
    private final Set<String> validUrlPathList;
    private final List<String> urlPaths;
    public RequestHandler(BufferedReader in) throws IOException {

        String requestMessage = in.readLine();
        validUrlPathList = Stream.of("", "index.html", "echo")
                                    .collect(Collectors.toSet());
        String[] req = requestMessage.split(" ");
        urlPaths = Arrays.stream(req[1].split("/"))
                .filter(s -> !s.isEmpty())
                .toList();
    }
    boolean validateURLPath(){
        if(urlPaths.isEmpty()) return true;
        return validUrlPathList.contains(urlPaths.getFirst());
    }
    String getPathVariable(){
        return urlPaths.size() > 1 ? urlPaths.getLast(): null;
    }
}
