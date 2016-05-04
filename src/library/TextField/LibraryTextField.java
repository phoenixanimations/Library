package library.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;


import java.util.Collections;


public class LibraryTextField extends JTextField
{
	private static final long serialVersionUID = 4447203274975665232L;
	private int scroll = 0;
	protected List<String> lastString = new ArrayList<String>();
	
	public LibraryTextField ()
	{
		
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (!getText().equals("")) 
				{
					lastString.add(getText());
					Collections.reverse(lastString);
				}
			}
		});
		addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) 
			{		
				if (e.getKeyCode() == KeyEvent.VK_UP)
				{
					if (lastString.size() > scroll)
					{
						setText(lastString.get(scroll));
						scroll++;
					}
					else 
					{
						scroll = lastString.size() - 1;
						setText(lastString.get(scroll));
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					if (scroll > 0)
					{
						scroll--;
						setText(lastString.get(scroll));
					}
					else
					{
						scroll = 0;
						setText("");						
					}
				}
//				System.out.println(scroll);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
}