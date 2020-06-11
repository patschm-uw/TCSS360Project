import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testDeleteFail() {
        Room room = new Room("Kitchen","\\HOME\\");
        String path = System.getProperty("user.dir");
        Items item = new Items(path+"\\HOME\\Room 1\\item","item");
        room.deleteItem(item);
    }
    
    @Test
    void ATestItemPathDelete() throws IOException {
        Room room = new Room("Kitchen","\\");
        String path = System.getProperty("user.dir");
        Items thing = new Items(path+"\\HOME\\Room 1\\thing","thing");
        String deletedFile = "C:\\Users\\Patrick Schmeichel\\TCSSWorkspace\\aboutScreen\\HOME\\Room 1\\thing\\A.txt";
        thing.getItemList();
        File file = new File(deletedFile);
        thing.deleteFile("A.txt");
        assertFalse(file.isFile());
    }
    
    @Test
    void testRoomDeleteItem() {
        Room room = new Room("Kitchen","\\");
        String path = System.getProperty("user.dir");
        Items thing = new Items(path+"\\HOME\\Room 1\\thing","thing");
        System.out.println(thing.getItemList());
        room.addItem(thing);
        room.deleteItem(thing);
        File file = thing.getFile();
        List<String> list = new LinkedList<String>();
        assertFalse(file.isFile());
    }
    
    @Test
    void testRoomDownload() throws IOException {
        Room room = new Room("Kitchen","\\");
        String path = System.getProperty("user.dir");
        Items thing = new Items(path+"\\HOME\\Room 1\\thing","thing");
        System.out.println(thing.getItemList());
        String downloads = System.getProperty("user.home")+"\\Downloads\\";
        File file = new File(downloads);
        room.addItem(thing);
        thing.downloadItem();
        room.downloadRoom();
        File test = new File(System.getProperty("user.home")+"\\Downloads\\B.txt");
    }
}
