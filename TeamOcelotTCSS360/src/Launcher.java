import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Main method for the project that launches the Home Window
 * @author Patrick Schmeichel
 * 
 */
public class Launcher {
    
    public static void main(String[] args) throws IOException {
        RoomList rlist = new RoomList();
        //create main folder
        String path = "HOME";
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        path = (String) System.getProperty("user.dir") + File.separator + "HOME";
        path = path.replace(File.separator, "\\\\");
        
        String roomPath = path + File.separator + rlist.getList().getSelectedValue();
        System.out.println(path);
        System.out.println(rlist.getList().getSelectedValue());
    }
}
