import java.io.File;
import java.io.IOException;

/**
 * Main method for the project that launches the Home Window
 * @author Patrick Schmeichel
 * 
 */
public class Launcher {
    
    public static void main(String[] args) throws IOException {
        RoomList rlist = new RoomList();
        //create main folder
        String path = System.getProperty("user.dir")+"\\HOME";
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        path = (String) System.getProperty("user.dir") + File.separator + "HOME";
        path = path.replace(File.separator, "\\\\");
        
        String roomPath = path + File.separator + rlist.getList().getSelectedValue();
    }
}
