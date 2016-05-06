package library.Pane;

import java.awt.Desktop;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import java.util.stream.Collectors;

//import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import library.File.LibraryFile;
import library.TextField.LibrarySearchBar;
import system.Queue;
import system.XML;

public class LibraryTextTagsImagePane extends JSplitPane
{
	private static final long serialVersionUID = 9086829730005962851L;
	
	public LibraryTextTagsImagePane (XML xmlCatalog)
	{
		LibrarySearchBar search = new LibrarySearchBar();
		JList <String> selectFiles = new JList<String>();

		LibraryShowTags tags = new LibraryShowTags(xmlCatalog);
		Queue queue = new Queue();
		
		search.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (search.getText().toLowerCase().contains("[rename file]") && !selectFiles.isSelectionEmpty())
				{
					System.out.println(selectFiles.getSelectedIndex());
					LibraryFile renameLibraryFile = search.getSortedCatalog().get(selectFiles.getSelectedIndex());
					String fileName = search.getText().split("]")[1];
					try 
					{
						xmlCatalog.renameFile(renameLibraryFile, fileName);
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					String[] names = new String [search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).size()];
					search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).toArray(names);
					selectFiles.setListData(names); 
					return;
				}
				
				search.onActionListener(xmlCatalog, queue);
				String[] names = new String [search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).size()];
				search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).toArray(names);
				selectFiles.setListData(names);
				selectFiles.clearSelection();
			}
		});
		
		selectFiles.addListSelectionListener(new ListSelectionListener() 
		{	
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				if (selectFiles.getSelectedIndex() < 0)
				{
					return;
				}
				LibraryFile selectedLibraryFile = search.getSortedCatalog().get(selectFiles.getSelectedIndex());
				tags.show(selectedLibraryFile);
				
//				String path = selectedLibraryFile.path;
//				ImageIcon imageIcon = new ImageIcon(path); //Scaling the image
//				int width = imageIcon.getIconWidth()/3;
//				if (width > 1000)
//				{
//					width *= .4;
//				}
//				image.labelImage.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(width, -1, Image.SCALE_FAST)));
			}
		});
		
		selectFiles.addKeyListener(new KeyListener() 
		{	
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) 
			{
				LibraryFile selectedLibraryFileKey = search.getSortedCatalog().get(selectFiles.getSelectedIndex());
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					try 
					{
						Desktop.getDesktop().open(new File(selectedLibraryFileKey.path));
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					queue.addToQue(selectedLibraryFileKey.path);
				}
				
				if (e.getKeyCode() == KeyEvent.VK_Q)
				{
					try 
					{
						queue.openFiles();
					} 
					catch (IOException e2) 
					{
						e2.printStackTrace();
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SLASH)
				{
					search.setText(selectedLibraryFileKey.path);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
//		JSplitPane imageTags = new JSplitPane(JSplitPane.VERTICAL_SPLIT,image,tags);
//		imageTags.setDividerLocation(340);
//		JSplitPane scrollImageTags = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(selectFiles),imageTags);		
		JSplitPane scrollImageTags = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(selectFiles),tags);
		scrollImageTags.setDividerLocation(360);
		scrollImageTags.setResizeWeight(1d);
		orientation = JSplitPane.VERTICAL_SPLIT;
		setDividerSize(1);
		setTopComponent(search);
		setBottomComponent(scrollImageTags);
		updateUI();
	}
}