package MainPack;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// parse the CSV file and return a HashMap of rows values
		CSVfileReader parser =new CSVfileReader("src/csvFiles/email.csv");
		parser.setRowsValue();
		HashMap< Integer  , Vector<String> >  map =parser.getRowsValue();
		// builde dom file (pass the HashMap as a parameter)
		DomBuilder domBuilder=new DomBuilder( map);
		domBuilder.buildDOM();
		// get the input in "xmlFiles" Pack
		domBuilder.Transform();
       
	}

}
