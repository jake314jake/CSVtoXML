# CSVtoXML
>> in Order to build the DOM tree from a CSV file (Ouput : the XML correspondent file )

{
<!ELEMENT csv (rows)>   <----------- Root elm

<!ELEMENT rows (row+)>

<!ELEMENT row (columns)>

<!ELEMENT columns (column+)>

<!ELEMENT column (#PCDATA)>

<!ATTLIST column   columnId CDATA #REQUIRED>
}
  
 ## EX:
 ************************************************************************
## the CSV file:( CSVtoDOM\src\csvFiles\email.csv)

Login email;Identifier;First name;Last name

lauraexample.com;2070;Laura;Grey

craigexample.com;4081;Craig;Johnson

maryexample.com;9346;Mary;Jenkins

jamieexample.com;5079;Jamie;Smith
 ************************************************************************
## the Xmlfile:( CSVtoDOM\src\csvFiles\email.xml)
<csv>    <----------- Root elm
                      
<rows>
 
<row>
 
<columns>
 
<column columnId="Login email">lauraexample.com</column>
 
<column columnId="Identifier">2070</column>
 
<column columnId="First name">Laura</column>
 
<column columnId="Last name">Grey</column>
 
</columns>  
 
<row>
 
<rows>
      .......
</csv>
**********************************************************
 
## class info:(in MainPack)
 
Main.java ----------------> main class
 
CSVfileReader.java -----------> parse csv file ( Delimiter,rows ,column)
 
DomBuilder.java --------------> build the dom tree (output xml file)
