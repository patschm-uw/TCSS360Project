import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {

    private Room room;
    private String path, deletedFile;
    private Items thing;
    
    
    @BeforeEach
    void setup() {
        room = new Room("Kitchen","\\HOME\\");
        path = System.getProperty("user.dir");
        thing = new Items(path+"\\HOME\\Room 1\\thing","thing");
        deletedFile = path + "\\HOME\\Room 1\\thing\\test.txt";
        File fileThing = new File(path+"\\HOME\\Room 1\\thing");
        File fileTest = new File(fileThing.getAbsolutePath()+"\\test.txt");
        fileThing.mkdir();
        fileTest.mkdir();
    }
    
    @Test
    void testDeleteFailAndPass() {
        room.deleteItem(thing);
        room.addItem(thing);
        room.deleteItem(thing);
        assertTrue(room.getIndex(thing)==-1);
    }
    
    @Test
    void ATestItemPathDelete() throws IOException {
        String deletedFile = path+"\\HOME\\Room 1\\thing\\A.txt";
        thing.getItemList();
        File fileA = new File(deletedFile);
        thing.deleteFile("A.txt");
        assertFalse(fileA.isFile());
    }
    
    @Test
    void testRoomDeleteItem() {
        System.out.println(thing.getItemList());
        File fileCheck = thing.getFile();
        room.addItem(thing);
        room.deleteItem(thing);
        assertFalse(fileCheck.isFile());
    }
    
    @Test
    void testRoomDownload() throws IOException {
        System.out.println(thing.getItemList());
        String downloads = System.getProperty("user.home")+"\\Downloads\\";
        File file = new File(downloads);
        room.addItem(thing);
        thing.downloadItem();
        room.downloadRoom();
        File test = new File(System.getProperty("user.home")+"\\Downloads\\B.txt");
    }
}
