package com.txt.xml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.SAXException;

public class Processor

{
	public static void main(String[] args) throws TransformerConfigurationException, ParserConfigurationException {
		{
			try {
				new Txtxml().readit();
			} catch (SAXException e) {

				e.printStackTrace();
			}

		}
	}

}
