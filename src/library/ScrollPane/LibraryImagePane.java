package library.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class LibraryImagePane extends JScrollPane
{
	private static final long serialVersionUID = 5085259721822268968L;
	
	private static JLabel createLoadImage (String filePath)
	{
		JLabel labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(filePath));
		return labelImage;
	}
	
	public LibraryImagePane (String filePath)
	{
		super(createLoadImage(filePath));
		setBounds(20, 189, 300, 179);
	}
}
