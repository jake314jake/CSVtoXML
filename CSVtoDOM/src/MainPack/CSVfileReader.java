package MainPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class CSVfileReader {

File csvFile;
static final String  csvDelimiter=";";
Scanner scnr;
int rowsCount=0;
HashMap< Integer  , Vector<String> > csvRowsValue;

CSVfileReader(String url) throws FileNotFoundException{
	this.csvFile = new File(url);
    this.scnr = new Scanner(this.csvFile);
   
}
void setRowsValue() {
   this.csvRowsValue=new HashMap< Integer , Vector<String> >();
	while(scnr.hasNextLine()){
        String line = scnr.nextLine();
        Scanner scnrLine=new Scanner(line);
        scnrLine.useDelimiter(CSVfileReader.csvDelimiter);
        Vector<String> tmpColumnValue=new  Vector<String>();
        while(scnrLine.hasNextLine()) {
        	String value=scnrLine.next();
        	tmpColumnValue.add(value);
        }
        csvRowsValue.put(new Integer(rowsCount),tmpColumnValue );
        rowsCount++;
        
    }      
	this.getInfo();
}
HashMap< Integer  , Vector<String> > getRowsValue(){
	return this.csvRowsValue;
}
private void getInfo() {
	System.out.println(this.csvRowsValue);
}
public static void main(String[] args) throws FileNotFoundException {
	
	
}
	
}
