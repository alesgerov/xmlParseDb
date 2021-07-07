package xml.parse.sax;

import jdbc.works.JdbcOracleFunctions;
import jdbc.works.JdbcUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.parse.Employee;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;

public class EmployeeHandler extends DefaultHandler {
    Employee employee=null;

    HashMap <String ,Boolean> onOff=new HashMap<>();
    JdbcOracleFunctions jdbcOracleFunctions=new JdbcOracleFunctions();
    Connection connection=null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("employee")){
            employee=new Employee();
        }
        onOff.put(qName,true);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("employee")) {
            jdbcOracleFunctions.addEmployee(employee);
            System.out.println(localName);
            employee = null;
        }

        onOff.put(qName,false);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data=new String(ch,start,length);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (onOff.getOrDefault("id",false)){
            employee.setId(Long.parseLong(data));
        }else if (onOff.getOrDefault("first_name",false)){
            employee.setFirst_name(data);
        }else if (onOff.getOrDefault("last_name",false)){
            employee.setLast_name(data);
        }else if (onOff.getOrDefault("hire_date",false)){
            employee.setHire_date(LocalDate.parse(data,formatter));
        }else if (onOff.getOrDefault("salary",false)){
            employee.setSalary(BigDecimal.valueOf(Long.parseLong(data)));
        }
    }

    @Override
    public void startDocument() throws SAXException {

        System.out.println("Process started!");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Process ended!");

    }

    public EmployeeHandler() {
        super();
    }



}
