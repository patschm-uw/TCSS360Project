import java.io.File;
import java.io.IOException;




/**
 * Main method for the project that launches the Home Window
 * @author Patrick Schmeichel
 * 
 */
public class Launcher {
    
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "HOME";
        File dir = new File(path);
        if(!dir.exists()) {
        	dir.mkdir();
        }
    	RoomList Home = new RoomList();
    	
    }
}
