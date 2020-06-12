import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.Test;

/**
* Show all the rooms in the house
* @author Kezeba Yifat
* @Team Ocelot
* @Item version 
*/
class uploadScreenTest {

	@Test
	void testCopy() throws IOException {
		Path src = Paths.get( "C:\\Users\\Kezeba\\Desktop\\text.txt");
        Path dest = Paths.get("C:\\Users\\Kezeba\\git\\TCSS360Project\\SettingScreen\\HOME\\Bath");
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
	}
}
