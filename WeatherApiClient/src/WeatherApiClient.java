import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApiClient {

    public static void main(String[] args) {
        try {
            // Delhi coordinates
            String apiUrl = "2e48cd04529343dca3844331250208";

            // Setup connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status == 200) {
                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Parse JSON
                JSONObject json = new JSONObject(response.toString());
                JSONObject weather = json.getJSONObject("current_weather");

                // Print weather info
                System.out.println("===== Delhi Weather =====");
                System.out.println("Temperature: " + weather.getDouble("temperature") + " °C");
                System.out.println("Wind Speed : " + weather.getDouble("windspeed") + " km/h");
                System.out.println("Time       : " + weather.getString("time"));
                System.out.println("=========================");
            } else {
                System.out.println("Failed to fetch data. HTTP response code: " + status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
