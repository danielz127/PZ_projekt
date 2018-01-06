import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Mateusz Korwel
 */
public class BazaParser {

    ArrayList<String> daneBazy;

    public BazaParser() {
        daneBazy = new ArrayList<>();
    }

    public ArrayList<String> getDaneBazy() {
        return daneBazy;
    }

    public void pobierzXML(){
        try {
            File file = new File("src/main/resources/plik.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeLst = doc.getElementsByTagName("Baza");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;

                    NodeList fstNmElmntLst = fstElmnt
                            .getElementsByTagName("login");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();

                    NodeList lstNmElmntLst = fstElmnt
                            .getElementsByTagName("haslo");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();

                    daneBazy.add(fstElmnt.getAttribute("url"));
                    daneBazy.add(((Node) fstNm.item(0)).getNodeValue());
                    daneBazy.add (((Node) lstNm.item(0)).getNodeValue());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}