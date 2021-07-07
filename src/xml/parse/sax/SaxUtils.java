package xml.parse.sax;

import jdbc.works.JdbcUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.sql.Connection;

public class SaxUtils {

    private  EmployeeHandler  openConnectionXml(String fileName,EmployeeHandler handler){
        try {

            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser saxParser=factory.newSAXParser();
            saxParser.parse(fileName,handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return  handler;
    }

    public  void runSaxParser(){
        EmployeeHandler handler=this.openConnectionXml("employees.xml",new EmployeeHandler());
    }


}
