import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;

public class OpenWebsiteByName {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt the user to enter a website name
            System.out.println("Enter the website you want to open (e.g., google, firefox, safari, edge, youtube): ");
            String websiteName = scanner.nextLine().toLowerCase().trim();

            // Open the corresponding website based on the website name
            openWebsite(websiteName);
        }
    }

    // Method to open the corresponding website based on the website name
    private static void openWebsite(String websiteName) {
        String url = "";

        switch (websiteName) {
            case "google":
                url = "https://www.google.com/";
                break;
            case "firefox":
                url = "https://www.mozilla.org/firefox/";
                break;
            case "safari":
                url = "https://www.apple.com/safari/";
                break;
            case "edge":
                url = "https://www.microsoft.com/edge/";
                break;
            case "youtube":
                url = "https://www.youtube.com/";
                break;
            default:
                System.out.println("Invalid website name. Please enter a valid website name.");
                return;
        }

        // Open the website in the default browser
        try {
            URI uri = new URI(url);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop is not supported on your system.");
            }
        } catch (Exception e) {
            System.out.println("Error opening the website: " + e.getMessage());
        }
    }
}
