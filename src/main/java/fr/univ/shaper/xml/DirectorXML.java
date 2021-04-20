package fr.univ.shaper.xml;


import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicStateListener;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DirectorXML {

    private final GraphicBuilder builder;

    private String filename;

    public DirectorXML (GraphicBuilder builder, String filename) {
        this.builder = builder;
        this.filename = filename;
    }

    public void load(GraphicStateListener listener) {
        InputSource is = null;
        try {
            is = new InputSource(new BufferedInputStream(new
                    FileInputStream(filename) ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = null;
        try {
            sp = spf.newSAXParser();
        } catch (ParserConfigurationException e) {
            System.out.println("Exception capturée newSAXParser 1 : " + e);
        } catch (SAXException e) {
            System.out.println("Exception capturée newSAXParser 2 : " + e);
        }
        XMLReader xr = null;
        try {
            assert sp != null;
            xr = sp.getXMLReader();
        } catch (SAXException e) {
            System.out.println("Exception capturée getXMLReader : " + e);
        }
        DrawingHandler handler = new DrawingHandler(builder);
        assert xr != null;
        xr.setContentHandler(handler) ;
        xr.setErrorHandler(handler) ;
        try {
            xr.parse(is) ;
        } catch ( Exception e ) {
            System.out.println("Exception capturée xr.parse : ");
            e.printStackTrace();
        }

        listener.event(handler.getDrawing());
    }
}




