import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class OpenAIApiExample {
    public static void main(String[] args) {
        String apiKey = "your-api-key";
        String productFeatures = "6.5-inch display, 128GB storage, dual-camera";

        try {
            URI uri = new URI("https://api.openai.com/v1/completions");
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            String data = String.format(
                    "{\"model\": \"text-davinci-003\", \"prompt\": \"Write a compelling product description for a new smartphone with the following features: %s.\", \"max_tokens\": 150, \"temperature\": 0.7}",
                    productFeatures);

            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            Scanner scanner;
            if (connection.getResponseCode() == 200) {
                scanner = new Scanner(connection.getInputStream());
            } else {
                scanner = new Scanner(connection.getErrorStream());
            }
            scanner.useDelimiter("\\Z");
            String response = scanner.next();
            scanner.close();

            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
