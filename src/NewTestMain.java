import jdbc.works.JdbcOracleFunctions;
import jdbc.works.JdbcUtils;
import xml.parse.stax.StaxParserRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NewTestMain {
    public static void main(String[] args) {
        StaxParserRunner staxParserRunner=new StaxParserRunner();
        JdbcOracleFunctions oracleFunctions=new JdbcOracleFunctions();
        try {
            staxParserRunner.run(new FileInputStream("employees.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
