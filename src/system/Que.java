package system;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Que 
{
	private List<String> que = new ArrayList<String>();
	public void addToQue (String path)
	{
		que.add(path);
	}
	
	public void openFiles () throws IOException
	{	
		for (String path : que) 
		{
			Desktop.getDesktop().open(new File(path));
		}	
		que.clear();
	}
	
	
}