package system;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.*;
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
				Files.walk(Paths.get("../Library"))
				 	 .filter(Files::isRegularFile)
				 	 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  	  e.toString().toLowerCase().contains(".png") ||
					  	   	  	  e.toString().toLowerCase().contains(".tiff") ||
					  	   	  	  e.toString().toLowerCase().contains(".mkv") ||
					  	   	  	  e.toString().toLowerCase().contains(".mp4") ||
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
			 	outputter.output(createDocument, new FileWriter(new File("data.xml")));
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
			 								catalog.add(new LibraryFile(Integer.parseInt(c.getChildText("id")),c.getChildText("name"), c.getChildText("path"), c.getChildText("extension"),tags));
			 							});
    }
	
	public void saveXML ()
	{
		try 
		{
			outputter.setFormat(Format.getCompactFormat());
			outputter.output(doc, new FileWriter(new File("data.xml")));
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
}