package library.Pane;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.media.jai.codecimpl.util.ImagingException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import library.File.LibraryFile;

public class LibraryTextTagsImagePane extends JSplitPane
{
	private static final long serialVersionUID = 9086829730005962851L;
//	public JScrollPane CreateTextTagsImagePane (List<LibraryFile> sortedCatalog)
//	{
//		LibraryTextScrollPane TextScroll = new LibraryTextScrollPane(sortedCatalog);
//		LibraryImagePane Image;
//		LibraryShowTags ShowTags;
//		TextScroll.textScroll.addListSelectionListener(new ListSelectionListener() 
//		{
//			@Override
//			public void valueChanged(ListSelectionEvent e) 
//			{
//				System.out.println("This does what I think it does " + TextScroll.textScroll.getSelectedIndex());				
//			}
//		});
//		
//		JScrollPane TagsImagePane = new JScrollPane();
//		JScrollPane TextPane = new JScrollPane();
//		return TagsImagePane;
//	}
	
	public LibraryTextTagsImagePane (List<LibraryFile> sortedCatalog)
	{
				
		LibraryTextScrollPane TextScroll = new LibraryTextScrollPane(sortedCatalog);
		LibraryImagePane Image = new LibraryImagePane();	
		TextScroll.textScroll.addListSelectionListener(new ListSelectionListener() 
		{	
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				System.out.println("This does what I think it does " + TextScroll.textScroll.getSelectedIndex());	
				String path = sortedCatalog.get(TextScroll.textScroll.getSelectedIndex()).path;
				Image.labelImage.setIcon(new ImageIcon(path));
			}
		});
		setLeftComponent(TextScroll);
		setRightComponent(Image);
		setBounds(6, 94, 588, 350);
	}
}
