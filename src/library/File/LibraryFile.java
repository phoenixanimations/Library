package library.File;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LibraryFile 
{
	public String name;
	public File file;
	public List<String> tags = new ArrayList<>();
	public LibraryFile(String assignName, File assignFile) 
	{
		name = assignName;
		file = assignFile;
	}
}

/*
	For the future: 
	public String name;
	public String path;
	public String extension;
	public List<String> tags = new ArrayList<>();
*/