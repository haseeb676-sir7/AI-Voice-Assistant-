import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class WeatherUpdate {
    @SuppressWarnings("deprecation")
    public void getWeather() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter city name for weather update: ");
            String city = scanner.nextLine().trim();

            // Replace YOUR_API_KEY with your actual API key
            String apiKey = "e5c6e6c72766487ca2343257252601";
            String urlString = "https://api.weatherapi.com/v1/current.xml?key=" + apiKey + "&q=" + city + "&aqi=no";

            // Create and configure the connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds timeout for connection
            connection.setReadTimeout(5000);   // 5 seconds timeout for reading data

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Parse and extract the XML data
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));

                String cityName = getTagValue("name", doc.getElementsByTagName("location").item(0));
                String country = getTagValue("country", doc.getElementsByTagName("location").item(0));
                String tempC = getTagValue("temp_c", doc.getElementsByTagName("current").item(0));
                String condition = getTagValue("text", ((Element) doc.getElementsByTagName("condition").item(0)));

                System.out.println("Weather in " + cityName + ", " + country + ":");
                System.out.println("Temperature: " + tempC + "°C");
                System.out.println("Condition: " + condition);
            } else {
                System.out.println("Error fetching weather data. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String getTagValue(String tag, Node node) {
        if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            NodeList nList = element.getElementsByTagName(tag);
            if (nList != null && nList.getLength() > 0) {
                return nList.item(0).getTextContent();
            }
        }
        return "N/A";
    }
}
