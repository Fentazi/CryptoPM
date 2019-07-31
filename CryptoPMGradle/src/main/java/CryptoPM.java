

import java.io.BufferedReader;
import java.io.StringWriter;
import java.util.prefs.Preferences;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class CryptoPM {

    final static String PREF_NAME = "PATH";
    public static Preferences prefs = Preferences.userNodeForPackage(CryptoPM.class);
    public static String path = "";
    public static BufferedReader in;
    public static OpenDataBase opn = new OpenDataBase();
    public static CreateNewBase create = new CreateNewBase();
    public static ShowPass shwpss = new ShowPass();
    public static DataBase database;
    public static AccChange accChange = new AccChange(shwpss, true);
    
    public static void main(String[] args) throws Exception {     
        opn.setVisible(true);
    }


    public static String toString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }
}
