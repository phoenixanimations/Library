package xml;

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
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import library.File.LibraryFile;


public class XML 
{
	private Element doc;
	private List<LibraryFile> catalog = new ArrayList<LibraryFile>();
	private XMLOutputter outputter = new XMLOutputter();
	
	public static void main (String[] args)
	{
		new XML();
	}
	
	public XML ()
    { 
		 try 
		 {
			if (!new File("data.xml").exists())
			{		 
			 	Element root = new Element("DATABASE");
			 	Document createDocument = new Document();
			 	
			 	AtomicInteger i = new AtomicInteger(0);
				Files.walk(Paths.get("./img"))
				 	 .filter(Files::isRegularFile)
				 	 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  	  e.toString().toLowerCase().contains(".png") ||
					  	   	  	  e.toString().toLowerCase().contains(".tiff"))
				 	 .forEachOrdered(e -> {
					 		 				Element xmlLibraryFile = new Element("image" + i.get());
					 		 				xmlLibraryFile.addContent(new Element("id").setText(i.toString()));
					 		 				xmlLibraryFile.addContent(new Element("name").setText(FilenameUtils.getBaseName(e.toString())));
					 		 				xmlLibraryFile.addContent(new Element("path").setText(e.toString()));
					 		 				xmlLibraryFile.addContent(new Element("extension").setText(FilenameUtils.getExtension(e.toString())));
					 		 				xmlLibraryFile.addContent(new Element("tags").addContent(new Element ("Default")));
					 		 				root.addContent(xmlLibraryFile);
					 		 				i.getAndIncrement();
					 			   		   });
				createDocument.setRootElement(root);
			 	outputter = new XMLOutputter();
			 	outputter.setFormat(Format.getPrettyFormat());
			 	outputter.output(createDocument, new FileWriter(new File("data.xml")));
			}
			doc = (Element) new SAXBuilder().build(new File("data.xml")).getRootElement();
		 }		 
		 catch (Exception e) 
		 {
		 	e.printStackTrace();
		 }
		 doc.getChildren().forEach(c -> catalog.add(new LibraryFile(Integer.parseInt(c.getChildText("id")),c.getChildText("name"), c.getChildText("path"), c.getChildText("extension"))));  
//		 addTag (catalog.get(0).id, "Hello");
//		 removeTag (catalog.get(0).id, "Hello");
    }
	
	private void saveXML ()
	{
		try 
		{
			outputter.setFormat(Format.getPrettyFormat());
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
		catalog.get(id).tags.add(tag);
		doc.getChildren().get(id).getChild("tags").addContent(new Element(tag));
		saveXML();
	}
	
	public void removeTag (int id, String tag)
	{
//		catalog.get(id).tags.remove(tag);
		doc.getChildren().get(id).getChild("tags").removeChild(tag);
		saveXML();
	}
}