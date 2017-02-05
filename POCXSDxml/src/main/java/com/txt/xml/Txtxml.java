package com.txt.xml;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

//SAX classes.
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

public class Txtxml {
	TransformerHandler th;
    AttributesImpl atts;
    BufferedReader in;
    StreamResult out;
    
	public void readit()
    {
        try{
        	FileInputStream  fis=	new FileInputStream (("E:\\Workspace\\input\\out\\poc.txt"));
        	in = new BufferedReader(new InputStreamReader(fis));
        	 out = new StreamResult("E:\\Workspace\\input\\out\\pocout.xml");
            
        	 initXML();
              String str;
              while ((str = in.readLine()) != null) {
                 process(str);
              }
              in.close();
              closeXML();
           }
           catch (Exception e) { e.printStackTrace(); }
      }
	
	
	public void initXML() throws ParserConfigurationException,TransformerConfigurationException,SAXException 
    {
          // JAXP + SAX
          SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
         
          th = tf.newTransformerHandler();
          Transformer serializer = th.getTransformer();
          serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
          // pretty XML output
          serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
          serializer.setOutputProperty(OutputKeys.INDENT,"yes");
          th.setResult(out);
          th.startDocument();
          atts = new AttributesImpl();
          th.startElement("http://www.demandware.com/xml/impex/inventory/2007-05-31","","inventory-list",atts);
    }

    public void process (String s) throws SAXException 
    {
        String [] elements = s.split("\\|");
        atts.clear();
             
        
        th.startElement("","","header list-id=",atts);
        th.characters(elements[0].toCharArray(),0,elements[0].length());
        th.startElement("","","default-instock",atts) ;
        th.characters(elements[1].toCharArray(),0,elements[1].length());
        th.endElement("","","default-instock");
        th.startElement("","","description",atts);
        th.characters(elements[2].toCharArray(),0,elements[2].length());
        th.endElement("","","description");
        th.startElement("","","use-bundle-inventory-only",atts);
        th.characters(elements[3].toCharArray(),0,elements[3].length());
        th.endElement("","","use-bundle-inventory-only");
        th.startElement("","","on-order",atts);
        th.characters(elements[4].toCharArray(),0,elements[4].length());
        th.endElement("","","on-order");
        th.endElement("","","header");
        th.startElement("","","records",atts);
        th.startElement("","","product-id=",atts);
        th.characters(elements[5].toCharArray(),0,elements[5].length());
        
        th.startElement("","","allocation",atts);
        th.characters(elements[6].toCharArray(),0,elements[6].length());
        th.endElement("","","allocation");
        
        th.startElement("","","allocation-timestamp",atts);
        th.characters(elements[7].toCharArray(),0,elements[7].length());
        th.endElement("","","allocation-timestamp");
        
        th.startElement("","","perpetual",atts);
        th.characters(elements[8].toCharArray(),0,elements[8].length());
        th.endElement("","","perpetual");
        
        th.startElement("","","preorder-backorder-handling",atts);
        th.characters(elements[9].toCharArray(),0,elements[9].length());
        th.endElement("","","preorder-backorder-handling");
        
        th.startElement("","","in-stock-date",atts);
        th.characters(elements[10].toCharArray(),0,elements[10].length());
        th.endElement("","","in-stock-date");
        
        th.startElement("","","in-stock-datetime",atts);
        th.characters(elements[11].toCharArray(),0,elements[11].length());
        th.endElement("","","in-stock-datetime");
        
        th.startElement("","","ats",atts);
        th.characters(elements[12].toCharArray(),0,elements[12].length());
        th.endElement("","","ats");
        
        th.startElement("","","on-order",atts);
        th.characters(elements[13].toCharArray(),0,elements[13].length());
        th.endElement("","","on-order");
        
        th.startElement("","","turnover",atts);
        th.characters(elements[14].toCharArray(),0,elements[14].length());
        th.endElement("","","turnover");
        
        th.startElement("","","custom-attributes",atts);
        
        th.startElement("","","custom-attribute attribute-id=",atts);
        th.characters(elements[15].toCharArray(),0,elements[15].length());
        th.startElement("","","",atts);
        th.characters(elements[19].toCharArray(),0,elements[19].length());
        th.endElement("","","custom-attribute");
        
        th.startElement("","","custom-attribute attribute-id=",atts);
        th.characters(elements[16].toCharArray(),0,elements[16].length());
        th.startElement("","","",atts);
        th.characters(elements[19].toCharArray(),0,elements[19].length());
        th.endElement("","","custom-attribute");
        
        th.startElement("","","custom-attribute attribute-id=",atts);
        th.characters(elements[17].toCharArray(),0,elements[17].length());
        th.startElement("","","",atts);
        th.characters(elements[19].toCharArray(),0,elements[19].length());
        th.endElement("","","custom-attribute");
        
        th.startElement("","","custom-attribute attribute-id=",atts);
        th.characters(elements[18].toCharArray(),0,elements[18].length());
        th.startElement("","","",atts);
        th.characters(elements[19].toCharArray(),0,elements[19].length());
        th.endElement("","","custom-attribute");
        th.endElement("","","custom-attributes");
        
        th.endElement("","","record");
        th.endElement("","","records");
        
        th.endElement("","","inventory-list");
        
    }
 
    public void closeXML()throws SAXException{
    { 
        th.endElement("","","inventory");
        th.endDocument();  
    }
}
}