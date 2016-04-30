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
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.sun.xml.internal.org.jvnet.fastinfoset.VocabularyApplicationData;

import library.File.LibraryFile;
import sun.launcher.resources.launcher;

public class XML 
{
	
	private Element doc;
	private List<LibraryFile> catalog = new ArrayList<LibraryFile>();
	
	public static void main (String[] args)
	{
		new XML();
	}
	
	public XML ()
    { 
		 try 
		 {
			if (new File("data.xml").exists())
			{
				doc = (Element) new SAXBuilder().build(new File("data.xml")).getRootElement();
			} 
			else
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
				 		 				xmlLibraryFile.addContent(new Element("tags").addContent("Default"));
				 		 				root.addContent(xmlLibraryFile);
				 		 				i.getAndIncrement();
					 			   });
				createDocument.setRootElement(root);
			 	XMLOutputter outputter = new XMLOutputter();
			 	outputter.setFormat(Format.getPrettyFormat());
			 	outputter.output(createDocument, new FileWriter(new File("data.xml")));
			 	doc = (Element) new SAXBuilder().build(new File("data.xml")).getRootElement();
//			 	doc.getChildren().get(0).getChild("tags").addContent("hi");
			 	//Find out the correct way XML save/loading.
			 	
			}
		 }		 
		 catch (Exception e) 
		 {
		 	e.printStackTrace();
		 }
		 doc.getChildren().forEach(c -> catalog.add(new LibraryFile(Integer.parseInt(c.getChildText("id")),c.getChildText("name"), c.getChildText("path"), c.getChildText("extension"))));  
		 
		 
    }
	
	public void addTag (int id, String tag)
	{
		catalog.get(id).tags.add(tag);
		doc.getChildren().get(id).getChild("tags").addContent(tag);
	}
	
	public void removeTag (int id, String tag)
	{
		catalog.get(id).tags.remove(tag);
		doc.getChildren().get(id).getChild("tags").removeChild(tag);
	}
	
	public List<LibraryFile> getCatalog ()
	{
		return catalog;
	}
}