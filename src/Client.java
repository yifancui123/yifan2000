import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://13.238.167.130/weather"))
                .header("Accept", "text/event-stream")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(HttpResponse::body)
                .thenAccept(inputStream -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (!line.isBlank()){
                                String[] parts = line.split(" ");
                                if (parts.length == 5){
                                    String time = parts[0];
                                    String type = parts[1];
                                    String X = parts[2];
                                    String Y = parts[3];
                                    String value = parts[4];
                                    System.out.println("Time: "+ time+ " Type: " + type + " X: " + X + " Y: " + Y + " Value: " + value + " ");
                                }
                            }else {System.out.println("Received: " + line);}
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading Server Side Event (SSE) stream: " + e.getMessage());
                    }
                })
                .join(); // Wait for the async operation to complete
    }
}