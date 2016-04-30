package xml;

import java.io.File;
import java.io.FileWriter; 
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XML 
{
	public XML ()
    {
		//Create XML file 
    	//try to find the xml file if you can't create a new one.
		//load xml
		
		    	
    	
    	
    	
    	
    	
    	
    	
    	
    	 try 
         {
         	Element root = new Element("CONFIGURATION");
         	Document createDoc=new Document();

         	Element child1=new Element("BROWSER");
         	child1.addContent(new Element("Chrome").setText("Meh"));
         	child1.addContent(new Element("Safari").setText("Okay"));

         	Element child2=new Element("BASE");
         	child2.addContent("http:fut");
         	
         	Element child3=new Element("EMPLOYEE");
         	child3.addContent("Anhorn, Irene");
         	
         	root.addContent(child1);
         	root.addContent(child2);
         	root.addContent(child3);
         	createDoc.setRootElement(root);

         	XMLOutputter outter=new XMLOutputter();
         	outter.setFormat(Format.getPrettyFormat());
         	outter.output(createDoc, new FileWriter(new File("data.xml")));
         	
         	SAXBuilder builder = new SAXBuilder();
         	Element doc = (Element) builder.build(new File("data.xml")).getRootElement();
         	System.out.println(doc.getChild("BROWSER").getChildText("Chrome"));
         } 
    	 
         catch (Exception e) 
     	 {
         	e.printStackTrace();
         }
    }
}