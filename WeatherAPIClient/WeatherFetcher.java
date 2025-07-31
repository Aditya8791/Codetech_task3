import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherFetcher {

    // Replace this with your actual API key from https://openweathermap.org/api
    private static final String API_KEY = "your_actual_api_key";
    private static final String CITY = "Delhi";
    private static final String URL_STRING =
        "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY + "&units=metric";

    public static void main(String[] args) {
        try {
            URL url = new URL(URL_STRING);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();

            // Parse JSON
            JSONObject obj = new JSONObject(jsonData.toString());

            String cityName = obj.getString("name");
            JSONObject main = obj.getJSONObject("main");
            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            String description = obj.getJSONArray("weather").getJSONObject(0).getString("description");

            // Display output
            System.out.println("📍 City: " + cityName);
            System.out.println("🌡️ Temperature: " + temperature + " °C");
            System.out.println("💧 Humidity: " + humidity + "%");
            System.out.println("🌥️ Condition: " + description);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}

