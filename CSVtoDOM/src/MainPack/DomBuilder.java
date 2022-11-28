package MainPack;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * the file would be like 
 * <csv>    <---------|  root element
 *  <rows>
 *     <row>
 *       <columns>
 *         <column columnId="Loginemail"> laura@example.com</column>
 *         <column columnId="Identifier"> 2070</column>
 *         <column columnId="Firstname"> Laura</column>
 *            ...
 *       </columns>  
 *     </row>
 *     
 *     <row >
 *       ...
 *     </row>
 *     ...
 *  </rows>
 * </csv>
 * */
public class DomBuilder {
	static final String rootName="csv";
	HashMap< Integer  , Vector<String> > csvRowsValue;
	DocumentBuilderFactory domFactory;
	DocumentBuilder domBuilder;
	Document newDoc;
	DomBuilder(HashMap< Integer  , Vector<String> > csvRowsValue) throws ParserConfigurationException{
		this.csvRowsValue= csvRowsValue;
		this.domFactory = DocumentBuilderFactory.newInstance();
	    this.domBuilder = domFactory.newDocumentBuilder();
	    this.newDoc = domBuilder.newDocument();   
	}
	void buildDOM() {
		// <csv> root
		Element rootElement = newDoc.createElement(DomBuilder.rootName);
        newDoc.appendChild(rootElement);
        // <rows>
        Element rowsElement = newDoc.createElement("rows");
        rootElement.appendChild(rowsElement);
        // iterate throw keys ->>> <row>
        Vector<String> attributeList = null; //to hold the first row of metadata
        Vector<String> rowList;       // the rest of the rows
        for(Integer Index : csvRowsValue.keySet()) {
            
        	if(Index == 0) {
        		attributeList=new Vector<String>();
        		attributeList=csvRowsValue.get(Index);
        	}else {
        		 rowList=new Vector<String>();
        		 rowList=csvRowsValue.get(Index);
        		 // <row>
        		 Element rowElement = newDoc.createElement("row");
        		 rowsElement.appendChild(rowElement);
        		 // <columns>
        		 Element columnsElement = newDoc.createElement("columns");
        		 rowElement.appendChild(columnsElement);
        		 int columnCount=0;
        		 for(String columnVal : rowList) {
        			 // <column>  nodes
        			 Element columnElement = newDoc.createElement("column");
        			 columnsElement.appendChild(columnElement);
        			 //add attribute 
        			 columnElement.setAttribute("columnId", attributeList.elementAt(columnCount));
        			 // <column> txt value
        			 Node columntxtVal = newDoc.createTextNode(columnVal) ;
        			 columnElement.appendChild(columntxtVal);
        			 columnCount ++;
        		 }
        	}
        }
	}
   void Transform() throws TransformerFactoryConfigurationError, TransformerException {
	   Transformer transformer = TransformerFactory.newInstance().newTransformer();
       Result output = new StreamResult(new File("src/xmlFiles/email.xml"));
       Source input = new DOMSource(newDoc);
       transformer.transform(input, output);
   }
}
