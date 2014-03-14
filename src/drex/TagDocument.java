/**
 * 
 */
package drex;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;



/**
 * @author Pete
 *
 */
public class TagDocument {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Create an output writer
		PrintWriter xmlOut = null;
		try {
			xmlOut = new PrintWriter("output/temp-out.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Read the input file
		String contentForNLP = new String(Files.readAllBytes(Paths.get("/Users/Pete/Coding/test-core-nlp/input/alice-in-wonderland.txt")));
		
	    // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
	    Properties props = new Properties();
	    props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    
	    // create an empty Annotation just with the given text
	    Annotation document = new Annotation(contentForNLP);
	    
	    // run all Annotators on this text
	    pipeline.annotate(document);
	    
	    // Print out the pipeline XML
	    pipeline.xmlPrint(document, xmlOut);
	    

	}

}
