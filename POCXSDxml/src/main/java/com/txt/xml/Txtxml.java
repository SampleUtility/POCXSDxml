package com.txt.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

//SAX classes.
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

public class Txtxml {

	private final static String DEFAULT_INSTOCK = "false";
	private final static String BUNDLE_INVENTORY = "false";
	private final static String ON_ORDER = "false";
	private final static String PERPETUAL = "false";
	private final static String PREORDER_BACKORDER_HANDLING = "none";
	private final static String TURN_OVER = "0";
	private final static String ON_ORDER_VALUE = "0";
	private final static String HEAD = "header list-id=\"inventory-tso-us";
	private final static String DESC = "Product Sku Inventory TSO";

	TransformerHandler th;
	AttributesImpl atts;
	BufferedReader in;
	StreamResult out;

	public void readit() throws SAXException, TransformerConfigurationException, ParserConfigurationException {
		try {
			//System.out.print("PATH"+System.getenv("THE_PATH_ENV"));
			FileInputStream fis = new FileInputStream(("E:\\Workspace\\input\\out\\poc.txt"));
			in = new BufferedReader(new InputStreamReader(fis));
			out = new StreamResult("E:\\Workspace\\input\\out\\pocout.xml");
			String record;
			initXML();

			while ((record = in.readLine()) != null) {
				process(record);
			}
			in.close();

			closeXML();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initXML() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

		th = tf.newTransformerHandler();
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		// printing XML output
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		th.setResult(out);
		th.startDocument();

		th.startElement("http://www.demandware.com/xml/impex/inventory/2007-05-31", "", "inventory	", atts);
		th.startElement("", "", "inventory-list", atts);
		th.startElement("", "", HEAD, atts);
		th.startElement("", "", "default-instock", atts);
		th.characters(DEFAULT_INSTOCK.toCharArray(), 0, DEFAULT_INSTOCK.length());
		th.endElement("", "", "default-instock");

		th.startElement("", "", "description", atts);
		th.characters(DESC.toCharArray(), 0, DESC.length());
		th.endElement("", "", "description");

		th.startElement("", "", "use-bundle-inventory-only", atts);
		th.characters(BUNDLE_INVENTORY.toCharArray(), 0, BUNDLE_INVENTORY.length());
		th.endElement("", "", "use-bundle-inventory-only");

		th.startElement("", "", "on-order", atts);
		th.characters(ON_ORDER.toCharArray(), 0, ON_ORDER.length());
		th.endElement("", "", "on-order");
		th.endElement("", "", "header");
		th.startElement("", "", "records", atts);
		th.startElement("", "", "record", atts);
	}

	public void process(String str) throws SAXException {
		String[] elements = str.split("\\|");
		atts = new AttributesImpl();
		atts.clear();

		String proid = "product-id=" + elements[8] + "-tso-us";
		String qty_avail = elements[11];
		String qty_value = elements[12];
		String alloc = Double.toString(quantitycheck(qty_avail, qty_value));

		th.startElement("", "", proid, atts);

		th.startElement("", "", "allocation", atts);
		th.characters(alloc.toCharArray(), 0, alloc.length());
		th.endElement("", "", "allocation");

		th.startElement("", "", "allocation-timestamp", atts);
		th.characters(elements[2].toCharArray(), 0, elements[2].length());
		th.endElement("", "", "allocation-timestamp");

		th.startElement("", "", "perpetual", atts);
		th.characters(PERPETUAL.toCharArray(), 0, PERPETUAL.length());
		th.endElement("", "", "perpetual");

		th.startElement("", "", "preorder-backorder-handling", atts);
		th.characters(PREORDER_BACKORDER_HANDLING.toCharArray(), 0, PREORDER_BACKORDER_HANDLING.length());
		th.endElement("", "", "preorder-backorder-handling");

		th.startElement("", "", "in-stock-date", atts);
		th.characters(elements[2].toCharArray(), 0, elements[2].length());
		th.endElement("", "", "in-stock-date");

		th.startElement("", "", "in-stock-datetime", atts);
		th.characters(elements[2].toCharArray(), 0, elements[2].length());
		th.endElement("", "", "in-stock-datetime");

		th.startElement("", "", "ats", atts);
		th.characters(alloc.toCharArray(), 0, alloc.length());
		th.endElement("", "", "ats");

		th.startElement("", "", "on-order", atts);
		th.characters(ON_ORDER_VALUE.toCharArray(), 0, ON_ORDER_VALUE.length());
		th.endElement("", "", "on-order");

		th.startElement("", "", "turnover", atts);
		th.characters(TURN_OVER.toCharArray(), 0, TURN_OVER.length());
		th.endElement("", "", "turnover");

		th.endElement("", "", "record");

	}

	public void closeXML() throws SAXException {
		{
			th.endElement("", "", "records");
			th.endElement("", "", "inventory-list");
			th.endElement("", "", "inventory");
			th.endDocument();
		}
	}

	public double quantitycheck(String qty_avail, String qty_value) {
		double qty = 0.0;
		if (qty_value.equals("-")) {
			qty = Double.parseDouble("-" + qty_avail);
		} else

		{

			qty = Double.parseDouble(qty_avail);

		}
		return qty;

	}
}