import jdbc.works.JdbcOracleFunctions;
import jdbc.works.JdbcUtils;
import xml.parse.sax.EmployeeHandler;
import xml.parse.sax.SaxUtils;

public class TestMain {
    public static void main(String[] args) {
        JdbcOracleFunctions jdbcOracleFunctions=new JdbcOracleFunctions();
//        SaxUtils saxUtils=new SaxUtils();
//        saxUtils.runSaxParser();
//        jdbcOracleFunctions.showEmployees(JdbcUtils.open(null));
        jdbcOracleFunctions.deleteAllEmployees(JdbcUtils.open(null));
    }
}
