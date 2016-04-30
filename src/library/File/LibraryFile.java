package library.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryFile 
{
	public int id;
	public String name;
	public String path;
	public String extension;
	public List<String> tags = new ArrayList<>();
	public LibraryFile(int assignID,  String assignName, String assignPath, String assignExtension) 
	{
		id = assignID;
		name = assignName;
		path = assignPath;
		extension = assignExtension;
	}
}