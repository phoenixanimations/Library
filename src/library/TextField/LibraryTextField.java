package library.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class LibraryTextField extends JTextField
{
	private static final long serialVersionUID = 4447203274975665232L;
	private String lastString = new String("");
	
	public LibraryTextField ()
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				onActionListener ();
			}
		});	
	}
	
	protected void onActionListener ()
	{
		lastString = getText();
		setText("");
	}
	
	public String getLastString ()
	{
		return lastString;
	}
}