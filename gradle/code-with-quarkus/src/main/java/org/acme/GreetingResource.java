package org.acme;

//import jakarta.ws.rs.GET;              |
//import jakarta.ws.rs.Path;             |
//import jakarta.ws.rs.Produces;         |     Previous imports
//import jakarta.ws.rs.core.MediaType;   |
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String fileUrl = "";  // Update with the actual file URL
        String filePath = "";  // Update with the desired file path

        boolean fileDownloaded = downloadFileMethod(fileUrl, filePath);

        if (fileDownloaded) {
            return "File downloaded successfully";
        } else {
            return "Failed to download file";
        }
    }

    private boolean downloadFileMethod(String fileUrl, String filePath) {
        try {
            URL url = new URL(fileUrl);
            java.nio.file.Path targetPath = java.nio.file.Path.of(filePath);
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File downloaded successfully.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
