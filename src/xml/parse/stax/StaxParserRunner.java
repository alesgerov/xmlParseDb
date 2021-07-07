package xml.parse.stax;

import jdbc.works.JdbcOracleFunctions;
import xml.parse.Employee;
import xml.parse.sax.EmployeeHandler;
import xml.parse.sax.SaxUtils;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class StaxParserRunner {
    private Employee employee=null;
    private final JdbcOracleFunctions jdbcOracleFunctions=new JdbcOracleFunctions();
    private XMLStreamReader openXMLConnection(FileInputStream fileInputStream){
        XMLStreamReader xmlStreamReader=null;
        try {
            XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
            xmlStreamReader=xmlInputFactory.createXMLStreamReader(fileInputStream);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlStreamReader;
    }

    public  void run(FileInputStream fileInputStream){
        HashMap <String,Boolean> onOff=new HashMap<>();


        XMLStreamReader reader=this.openXMLConnection(fileInputStream);
        try {
            while (reader.hasNext()) {
                int eventType=reader.getEventType();
                if (eventType== XMLStreamConstants.START_DOCUMENT){
                    System.out.println("Process started!");
                }else if (eventType==XMLStreamConstants.END_DOCUMENT){
                    System.out.println("Process ended!");
                }else if (eventType==XMLStreamConstants.START_ELEMENT){
                    onOff.put(reader.getLocalName(),true);
                    if (reader.getLocalName().equals("employee")){
                        employee=new Employee();
                    }
                }else if (eventType==XMLStreamConstants.END_ELEMENT){
                    if (reader.getLocalName().equals("employee")){
                        jdbcOracleFunctions.addEmployee(employee);
                        //System.out.println(employee.getFirst_name());
                        employee=null;
                    }
                    onOff.put(reader.getLocalName(),false);
                }else if (eventType==XMLStreamConstants.CHARACTERS){
                    String data=reader.getText();
                    if (onOff.getOrDefault("id",false)){
                        employee.setId(Long.parseLong(data));
                    }else if (onOff.getOrDefault("first_name",false)){
                        employee.setFirst_name(data);
                    }else if (onOff.getOrDefault("last_name",false)){
                        employee.setLast_name(data);
                    }else if (onOff.getOrDefault("hire_date",false)){
                        employee.setHire_date(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    }else if (onOff.getOrDefault("salary",false)){
                        employee.setSalary(BigDecimal.valueOf(Long.parseLong(data)));
                    }
                }

                reader.next();


            }
        }catch (XMLStreamException e){
            e.printStackTrace();
        }
    }



}
