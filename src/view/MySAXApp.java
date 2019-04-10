package view;

import java.io.FileReader;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import model.Item;

public class MySAXApp extends DefaultHandler {

    private ArrayList<Item> itemList = new ArrayList<>();

    public static void main(String args[]) throws Exception {
	XMLReader xr = XMLReaderFactory.createXMLReader();
	MySAXApp handler = new MySAXApp();
	xr.setContentHandler(handler);
	xr.setErrorHandler(handler);

	FileReader r = new FileReader("resources/list_items.xml");
	xr.parse(new InputSource(r));
    }

    public MySAXApp() {
	super();
    }

//    public ArrayList<Item> getItems() {
//	return this.itemList;
//    }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    @Override
    public void startDocument() {
	System.out.println("Start document");
    }

    @Override
    public void endDocument() {
	System.out.println("End document");
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes atts) {
	if ("".equals(uri))
	    System.out.println("Start element: " + qName);
	if (qName.equals("item")) {
	    this.itemList.add(new Item());
	} else
	    System.out.println("Start element: {" + uri + "}" + name);
    }

    @Override
    public void endElement(String uri, String name, String qName) {
	if ("".equals(uri))
	    System.out.println("End element: " + qName);
	else
	    System.out.println("End element:   {" + uri + "}" + name);
    }

    @Override
    public void characters(char ch[], int start, int length) {
	System.out.print("Characters:    \"");
	String tmpStr = "";
	for (int i = start; i < start + length; i++) {
	    switch (ch[i]) {
	    case '\\':
		break;
	    case '"':
		break;
	    case '\n':
		break;
	    case '\r':
		break;
	    case '\t':
		break;
	    default:
		tmpStr += ch[i];
		break;
	    }
	}
	System.out.print(tmpStr);
	System.out.print("\"\n");
    }

}
