import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Settings {
    private boolean basketLoadEnable;
    private String basketLoadFileName;
    private String basketLoadFormat;

    private boolean basketSaveEnable;
    private String basketSaveFileName;
    private String basketSaveFormat;

    private boolean logEnable;
    private String logFileName;

    private File fileSave;

    public String getBasketLoadFileName() {
        return basketLoadFileName;
    }

    public boolean getBasketLoadEnable() {
        return basketLoadEnable;
    }

    public String getBasketLoadFormat() {
        return basketLoadFormat;
    }

    public File getFileSave() {
        return fileSave;
    }

    public boolean getBasketSaveEnable() {
        return basketSaveEnable;
    }

    public String getBasketSaveFileName() {
        return basketSaveFileName;
    }

    public String getBasketSaveFormat() {
        return basketSaveFormat;
    }

    public boolean getLogEnable() {
        return logEnable;
    }

    public void setBasketLoadEnable(boolean basketLoadEnable) {
        this.basketLoadEnable = basketLoadEnable;
    }

    public void setBasketLoadFileName(String basketLoadFileName) {
        this.basketLoadFileName = basketLoadFileName;
    }

    public void setBasketLoadFormat(String basketLoadFormat) {
        this.basketLoadFormat = basketLoadFormat;
    }

    public void setBasketSaveEnable(boolean basketSaveEnable) {
        this.basketSaveEnable = basketSaveEnable;
    }

    public void setBasketSaveFileName(String basketSaveFileName) {
        this.basketSaveFileName = basketSaveFileName;
    }

    public void setBasketSaveFormat(String basketSaveFormat) {
        this.basketSaveFormat = basketSaveFormat;
    }

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public void setFileSave(String name, String format) {
        this.fileSave = new File(name + "." + format);
    }

    public void getConfig() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document doc = null;
        try {
            doc = builder.parse(new File("shop.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Node config = doc.getDocumentElement();
        loadSettings(config);
    }

    public void loadSettings(Node config) {
        NodeList nodeList = config.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                Element element = (Element) currentNode;
                NamedNodeMap map = element.getAttributes();
                for (int a = 0; a < map.getLength(); a++) {
                    String nodeName = currentNode.getNodeName();
                    String attrName = map.item(a).getNodeName();
                    String attrValue = map.item(a).getNodeValue();
                    this.setSettings(nodeName, attrName, attrValue);
                }
                loadSettings(currentNode);
            }
        }
    }

    public void setSettings(String nodeName, String attrName, String atterValue) {
        if (nodeName.equals("enabled") && attrName.equals("basketLoadEnable")) {
            if (atterValue.equals("true")) {
                setBasketLoadEnable(true);
            } else {
                setBasketLoadEnable(false);
            }
        }
        if (nodeName.equals("fileName") && attrName.equals("basketLoadFileName")) {
            setBasketLoadFileName(atterValue);
        }
        if (nodeName.equals("format") && attrName.equals("basketLoadFormat")) {
            setBasketLoadFormat(atterValue);
        }
        if (nodeName.equals("enabled") && attrName.equals("basketSaveEnable")) {
            if (atterValue.equals("true")) {
                setBasketSaveEnable(true);
            } else {
                setBasketSaveEnable(false);
            }
        }
        if (nodeName.equals("fileName") && attrName.equals("basketSaveFileName")) {
            setBasketSaveFileName(atterValue);
        }
        if (nodeName.equals("format") && attrName.equals("basketSaveFormat")) {
            setBasketSaveFormat(atterValue);
        }
        if (nodeName.equals("enabled") && attrName.equals("logEnable")) {
            if (atterValue.equals("true")) {
                setLogEnable(true);
            } else {
                setLogEnable(false);
            }
        }
        if (nodeName.equals("fileName") && attrName.equals("logFileName")) {
            setLogFileName(atterValue);
        }
        setFileSave(basketSaveFileName, basketSaveFormat);
    }
}