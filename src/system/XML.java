package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;

import java.util.List;
import java.util.concurrent.atomic.*;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



import library.File.LibraryFile;

public class XML 
{
	private Element doc;
	private List<LibraryFile> catalog = new ArrayList<LibraryFile>();
	private XMLOutputter outputter = new XMLOutputter();
	private IllegalXMLCharacters illegal = new IllegalXMLCharacters();
	public XML ()
    { 
		 try 
		 {
			if (!new File("data.xml").exists())
			{		 
			 	Element root = new Element("DATABASE");
			 	Document createDocument = new Document();				
			 	AtomicInteger i = new AtomicInteger(0);
				Files.walk(Paths.get("./"))
				 	 .filter(Files::isRegularFile)
				 	 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  	  e.toString().toLowerCase().contains(".png") ||
					  	   	  	  e.toString().toLowerCase().contains(".tiff") ||
					  	   	  	  e.toString().toLowerCase().contains(".mkv") ||
					  	   	  	  e.toString().toLowerCase().contains(".mp4") ||
					  	   	  	  e.toString().toLowerCase().contains(".mov") ||
					  	   	  	  e.toString().toLowerCase().contains(".avi") ||
					  	   	  	  e.toString().toLowerCase().contains(".wmv") ||
					  	   	  	  e.toString().toLowerCase().contains(".txt") ||
					  	   	  	  e.toString().toLowerCase().contains(".pdf") ||
					  	   	  	  e.toString().toLowerCase().contains(".doc") ||
					  	   	  	  e.toString().toLowerCase().contains(".docx") ||
					  	   	  	  e.toString().toLowerCase().contains(".pages"))
				 	 .forEachOrdered(e -> {
					 		 				Element xmlLibraryFile = new Element("file" + i.get());
					 		 				xmlLibraryFile.addContent(new Element("id").setText(i.toString()));
					 		 				xmlLibraryFile.addContent(new Element("name").setText(FilenameUtils.getBaseName(e.toString())));
					 		 				xmlLibraryFile.addContent(new Element("path").setText(e.toString()));
					 		 				xmlLibraryFile.addContent(new Element("extension").setText(FilenameUtils.getExtension(e.toString())));
					 		 				xmlLibraryFile.addContent(new Element("tags").addContent(new Element ("Default")).addContent(new Element ("All")));
					 		 				root.addContent(xmlLibraryFile);
					 		 				i.getAndIncrement();
					 			   		   });
				
				createDocument.setRootElement(root);
			 	outputter = new XMLOutputter();
			 	outputter.setFormat(Format.getCompactFormat());
			 	FileWriter fileWriter = new FileWriter(new File("data.xml"));
			 	outputter.output(createDocument, fileWriter);
			 	fileWriter.close();
			}
			doc = (Element) new SAXBuilder().build(new File("data.xml")).getRootElement();
		 }		 
		 catch (Exception e) 
		 {
		 	e.printStackTrace();
		 }
		 doc.getChildren().forEach(c -> {
			 								List<String> tags = new ArrayList<String>();
			 								c.getChild("tags").getChildren().forEach(t -> tags.add(illegal.xmlToString(t.getName())));	
			 								catalog.add(new LibraryFile(Integer.parseInt(c.getChildText("id")),c.getChildText("name"), c.getChildText("path"), c.getChildText("extension"),tags.stream().sorted().collect(Collectors.toList())));
			 							});
    }
	
	public void saveXML ()
	{
		try 
		{
			outputter.setFormat(Format.getCompactFormat());
			FileWriter fileWriter = new FileWriter(new File("data.xml"));
			outputter.output(doc, fileWriter);
			fileWriter.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<LibraryFile> getCatalog ()
	{
		return catalog;
	}
	
	public void addTag (int id, String tag)
	{
		//Make it so tags are strings so you don't have to worry about the illegal characters.
		catalog.get(id).tags.remove(tag);
		catalog.get(id).tags.add(tag);
		tag = illegal.stringToXML(tag);
		doc.getChildren().get(id).getChild("tags").removeChild(tag);
		doc.getChildren().get(id).getChild("tags").addContent(new Element(tag));
	}
	
	public void removeTag (int id, String tag)
	{
		catalog.get(id).tags.remove(tag);
		tag = illegal.stringToXML(tag);
		doc.getChildren().get(id).getChild("tags").removeChild(tag);
	}

	public List<String> bList = new ArrayList<String>(); ///DELETE
	public void addBlacklistTag  (String tag)
	{
		bList.add(tag);
	}
	
	public void removeBlacklistTag  (String tag)
	{
		bList.remove(tag);
	}
	
	public String getBlacklistTag ()
	{
		String text = "";
		for (int i = 0; i < bList.size(); i++)
		{
			text += "-" + bList.get(i);
		}
		return text;
	}
	
	public void refresh()
	{
		List <LibraryFile> recent = new ArrayList<LibraryFile>();
		try 
		{
			AtomicInteger i = new AtomicInteger(0);
			Files.walk(Paths.get("./"))
			 .filter(Files::isRegularFile)
			 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
			  	   	  	  e.toString().toLowerCase().contains(".jpg") ||
			  	   	  	  e.toString().toLowerCase().contains(".png") ||
			  	   	  	  e.toString().toLowerCase().contains(".tiff") ||
			  	   	  	  e.toString().toLowerCase().contains(".mkv") ||
			  	   	  	  e.toString().toLowerCase().contains(".mp4") ||
			  	   	  	  e.toString().toLowerCase().contains(".mov") ||
			  	   	  	  e.toString().toLowerCase().contains(".avi") ||
			  	   	  	  e.toString().toLowerCase().contains(".wmv") ||
			  	   	  	  e.toString().toLowerCase().contains(".txt") ||
			  	   	  	  e.toString().toLowerCase().contains(".pdf") ||
			  	   	  	  e.toString().toLowerCase().contains(".doc") ||
			  	   	  	  e.toString().toLowerCase().contains(".docx") ||
			  	   	  	  e.toString().toLowerCase().contains(".pages"))
			 .forEachOrdered(e -> 
			 {
				 List<String> newTags = Arrays.asList("Default","All");
				 recent.add(new LibraryFile(i.intValue(), FilenameUtils.getBaseName(e.toString()), e.toString(), FilenameUtils.getExtension(e.toString()), newTags));
				 i.incrementAndGet();
			 });
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		if (recent.size() != catalog.size())
		{
			List<Integer> deleteID = new ArrayList<Integer>();
			catalog.forEach(c ->{recent.forEach(r -> 
			{
				if (c.path.equals(r.path))
				{
					deleteID.add(r.id);
				}
			});});
			
			Collections.sort(deleteID, Collections.reverseOrder());
			for (Integer integer : deleteID) 
			{
				recent.remove(integer.intValue());
			}
			
			if (recent.size() > 0)
			{
				AtomicInteger i = new AtomicInteger(catalog.size());
				recent.forEach(r -> 
				{
		 				Element xmlLibraryFile = new Element("file" + i.get());
		 				xmlLibraryFile.addContent(new Element("id").setText(i.toString()));
		 				xmlLibraryFile.addContent(new Element("name").setText(r.name));
		 				xmlLibraryFile.addContent(new Element("path").setText(r.path));
		 				xmlLibraryFile.addContent(new Element("extension").setText(r.extension));
		 				xmlLibraryFile.addContent(new Element("tags").addContent(new Element ("Default")).addContent(new Element ("All")).addContent(new Element("New")));
		 				doc.addContent(xmlLibraryFile);
		 				i.getAndIncrement();
				});
				catalog.clear();
				doc.getChildren().forEach(c -> 
				{
					List<String> tags = new ArrayList<String>();
					c.getChild("tags").getChildren().forEach(t -> tags.add(illegal.xmlToString(t.getName())));	
					catalog.add(new LibraryFile(Integer.parseInt(c.getChildText("id")),c.getChildText("name"), c.getChildText("path"), c.getChildText("extension"),tags.stream().sorted().collect(Collectors.toList())));
				});
			}
		}
	}
}