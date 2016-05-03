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
					 		 				xmlLibraryFile.addContent(new Element("tags").addContent(new Element ("Default")));
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
//					 		 				autoTag(e.toString(), "Photos", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Wildlife", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Insect", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Fish", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Animal", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "War", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Vehicle", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Texture", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Space", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Object", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Landscape", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Humans", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Anatomy", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Outfits", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Skeleton", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "History", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Entertainment", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Boat", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Documentary", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Artists", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Studios", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Website", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Film", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Storyboarding", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Vietnam", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Government", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Literature", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Disney", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Movie", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Screenplay", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Video", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Art", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Coding", xmlLibraryFile);
//					 		 				autoTag(e.toString(), "Reference", xmlLibraryFile);
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
					 					 	//DELETE THIS CODE//
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
		catalog.get(id).tags.add(tag);
		tag = illegal.stringToXML(tag);
		doc.getChildren().get(id).getChild("tags").addContent(new Element(tag));
	}
	
	public void removeTag (int id, String tag)
	{
		catalog.get(id).tags.remove(tag);
		tag = illegal.stringToXML(tag);
		doc.getChildren().get(id).getChild("tags").removeChild(tag);
	}
	
	//DELETE/CHANGE//
	//DELETE/CHANGE//
	//DELETE/CHANGE//
	//DELETE/CHANGE//
//	private void autoTag (String path, String tag, Element elementTag)
//	{
//		if (path.contains(tag))
//		{
//			elementTag.getChild("tags").addContent(new Element(tag));
//		}
//	}
	//DELETE/CHANGE//
	//DELETE/CHANGE//
	//DELETE/CHANGE//
	//DELETE/CHANGE//
}