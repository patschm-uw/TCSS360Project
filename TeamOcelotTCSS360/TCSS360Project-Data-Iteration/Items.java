import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * I tems class list out all the item from the folder path.
 * @author Kieu Trinh
 * @Team Ocelot
 * @Item version 
 */
public class Items {
	/**
	 * take the path string of the folder 
	 */
	private String path;
	/**
	 * The list string contain all the name of the item in the folder
	 */
	private List<String> fileContent;
	/**
	 * file the folder path
	 */
	private File file;
	
	/**
	 * The constructor that take the path string and initialize all the variables
	 * @param thepath
	 */
	public Items(String thepath) {
		path = thepath;
		fileContent = new ArrayList<String>();
		file = new File(thepath);
	}
	
	/**
	 * return all the item name in the folder
	 * @return list fileContent
	 */
	public List<String> getItemList(){
		for(String content : file.list()) {
			fileContent.add(content);
		}
		return fileContent;
	}
	/**
	 * return all the file path of the items in folder
	 * @return fileContent
	 */
	public File[] getItemPath(){
		return file.listFiles();
	}
	/**
	 * return the size of the fileContent list
	 * @return int
	 */
	public int getSize() {
		return fileContent.size();
	}
	/**
	 *Get the element of the list from the index 
	 * @param index
	 * @return
	 */
	public String getElement(int index) {
		return fileContent.get(index);
	}
	/**
	 * check if the folder contain the Item
	 * @param name
	 * @return true or false
	 */
	public boolean contain(String name) {
		boolean check = false;
		for (String file : fileContent) {
			if (file.equalsIgnoreCase(name)) {
				check = true;
			}
		}
		return check;
	}
	/**
	 * delete the item from the folder
	 * @param fileName
	 */
	public void deleteFile(String fileName) {
		File dFileName = new File(path + "\\"+fileName);
		if(contain(fileName)) {
			dFileName.delete();
		}
	}
	
}
