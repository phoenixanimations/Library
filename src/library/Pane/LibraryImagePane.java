package library.Pane;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class LibraryImagePane extends JScrollPane
{
	private static final long serialVersionUID = 5085259721822268968L;
	public JLabel labelImage = new JLabel();
	public LibraryImagePane ()
	{
		setViewportView(labelImage);
		setBounds(20, 189, 300, 179);
	}
}