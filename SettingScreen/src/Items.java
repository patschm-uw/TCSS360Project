import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Items {
	private String path;
	private List<String> fileContent;
	private File file;
	
	public Items(String thepath) {
		path = thepath;
		fileContent = new ArrayList<String>();
		file = new File(thepath);
	}
	
	public List<String> getFileList(){
		for(String content : file.list()) {
			fileContent.add(content);
		}
		return fileContent;
	}
	
	public int getSize() {
		return fileContent.size();
	}
	
	public String getElement(int index) {
		return fileContent.get(index);
	}
	
	public boolean contain(String name) {
		boolean check = false;
		for (String file : fileContent) {
			if (file.equalsIgnoreCase(name)) {
				check = true;
			}
		}
		return check;
	}
	
	public void deleteFile(String fileName) {
		File dFileName = new File(path + "\\"+fileName);
		if(contain(fileName)) {
			dFileName.delete();
		}
	}
	
}
