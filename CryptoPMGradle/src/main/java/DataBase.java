

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

public class DataBase {

    public String name;
    public ArrayList<Account> accounts;
    public String pass = "";
    public File file;
    public Account cur;

    public DataBase(String str) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();

        parser.parse(new InputSource(new StringReader(str)), handler);
        accounts = handler.result;
        name = handler.name;
    }

    private static class XMLHandler extends org.xml.sax.helpers.DefaultHandler {

        ArrayList<Account> result = new ArrayList<>();
        String name;

        @Override
        public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
            if (qName.equals("account")) {
                String site = attributes.getValue("site");
                String login = attributes.getValue("login");
                String pass = attributes.getValue("pass");
                String comm = attributes.getValue("comm");
                result.add(new Account(site, login, pass, comm));
            }
            if (qName.equals("DataBase")) {
                name = attributes.getValue("name");
            }
        }
    }

}
