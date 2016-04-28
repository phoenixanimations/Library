package library.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryFile 
{
	public String name;
	public String path;
	public String extension;
	public List<String> tags = new ArrayList<>();
	public LibraryFile(String assignName, String assignPath, String assignExtension) 
	{
		name = assignName;
		path = assignPath;
		extension = assignExtension;
	}
}