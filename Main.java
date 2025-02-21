import java.awt.Desktop;
import java.net.URI;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String userInput = args[0].toLowerCase();

            if (userInput.contains("weather")) {
                WeatherUpdate weather = new WeatherUpdate();
                weather.getWeather();
            } else if (userInput.contains("open")) {
                String website = userInput.replace("open", "").trim();
                if (!website.isEmpty()) {
                    openBrowser(website);
                } else {
                    System.out.println("Please specify a website.");
                }
            } else if (userInput.contains("chat")) {
                String query = extractChatQuery(userInput);
                if (!query.isEmpty()) {
                    String response = Chat.getChatResponse(query);
                    System.out.println(response);
                } else {
                    System.out.println("Please provide a query after 'chat'.");
                }
            } else {
                System.out.println("Sorry, I couldn't understand the command.");
            }
        } else {
            System.out.println("No input received.");
        }
    }

    // Method to open the browser with the given URL
    public static void openBrowser(String url) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            URI uri = new URI(url);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop is not supported on your system.");
            }
        } catch (Exception e) {
            System.out.println("Error opening the browser: " + e.getMessage());
        }
    }

    // Helper method to extract chat query
    private static String extractChatQuery(String input) {
        Pattern pattern = Pattern.compile("chat\\s*(.*)");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(1).trim() : "";
    }
}