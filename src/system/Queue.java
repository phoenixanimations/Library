package system;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Queue 
{
	private List<String> queue = new ArrayList<String>();
	public void addToQue (String path)
	{
		queue.add(path);
	}
	
	public void clear ()
	{
		queue.clear();
	}
	
	public void openFiles () throws IOException
	{	
		for (String path : queue) 
		{
			Desktop.getDesktop().open(new File(path));
		}	
		queue.clear();
	}
}