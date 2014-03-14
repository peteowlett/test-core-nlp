package drex;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class NLPDumpConverter {

	public static void main(String[] args) {
		
		
		try 
		{
			
			//  Read and Parse the XML File
			File myXMLFile = new File("output/temp-out.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(myXMLFile);
			doc.getDocumentElement().normalize();

			//  Open CSV file for writing nodes
            BufferedWriter output = new BufferedWriter(new FileWriter("output/nodes.csv", true));
			
			// Iterate through the tokens
			NodeList nList = doc.getElementsByTagName("token");
			for (int token = 0; token < nList.getLength(); token++) {
				
				Node node = nList.item(token);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					output.write(String.format("%s|%s|%s|%s|%s", 
						element.getAttribute("id"),
						element.getElementsByTagName("word").item(0).getTextContent(),	
						element.getElementsByTagName("lemma").item(0).getTextContent(),
						element.getElementsByTagName("POS").item(0).getTextContent(),
						element.getElementsByTagName("NER").item(0).getTextContent()));
					output.newLine();
					
				}
				
			}
			
			// Close the nodes file
            output.flush();
            output.close();
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
