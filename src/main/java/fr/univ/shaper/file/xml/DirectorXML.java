package fr.univ.shaper.file.xml;


import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicElement;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.core.element.noisy.NoisyGraphicFactory;
import fr.univ.shaper.core.exception.GraphicTypeNotFoundException;
import fr.univ.shaper.file.Director;
import fr.univ.shaper.util.Contract;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DirectorXML implements Director {

    private final Charset charset = StandardCharsets.UTF_8;

    private final GraphicBuilder builder;

    private File file;

    public DirectorXML(GraphicBuilder builder) {
        this.builder = builder;
    }

    public GraphicElement load(File file) {
        InputSource is = null;
        try {
            is = new InputSource(new BufferedInputStream(new
                    FileInputStream(file.getAbsolutePath()) ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = null;
        try {
            sp = spf.newSAXParser();
        } catch (ParserConfigurationException e) {
            System.out.println("Exception capturée newSAXParser 1 : " + e);
            return null;
        } catch (SAXException e) {
            System.out.println("Exception capturée newSAXParser 2 : " + e);
            return null;
        }

        XMLReader xr = null;
        try {
            assert sp != null;
            xr = sp.getXMLReader();
        } catch (SAXException e) {
            System.out.println("Exception capturée getXMLReader : " + e);
            return null;
        }
        DrawingHandler handler = new DrawingHandler(builder);
        assert xr != null;
        xr.setContentHandler(handler) ;
        xr.setErrorHandler(handler) ;

        NoisyGraphicFactory ngf;
        try {
            ngf = (NoisyGraphicFactory) GraphicFactoryHandler
                    .newInstance().getFactoryOf("Noisy");
            ngf.setGenerateNoise(false);
        } catch (GraphicTypeNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            xr.parse(is) ;
        } catch ( Exception e ) {
            System.out.println("Exception capturée xr.parse : ");
            e.printStackTrace();
        }

        ngf.setGenerateNoise(true);

        return handler.getDrawing();
    }

    @Override
    public void save(GraphicElement element) {
        // TODO save

    }

    @Override
    public void saveAs(File file, GraphicElement element) {
        Contract.assertThat(file != null, "Filename ne doit pas être null");
        Contract.assertThat(element != null, "element ne doit pas être null");
        // TODO la condition particulière ?!
        PrintWriter writer;
        try {
            writer = new PrintWriter(file, charset.toString());
        } catch (UnsupportedEncodingException e) {
            // TODO gérer les erreurs
            e.printStackTrace();
            return; // TODO très mauvais
        } catch (FileNotFoundException e) {
            return;
        }

        // ----------------------------------------------- //
        //                      HEAD                       //
        // ----------------------------------------------- //

        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"")
                .append(charset)
                .append("\"?>\n")
                .append("<!DOCTYPE drawing SYSTEM \"drawing.dtd\">\n")
                .append("<drawing xmlns=\"http://www.univ-rouen.fr/drawing\">\n");
        XmlGraphicVisitor v = new XmlGraphicVisitor(builder);
        element.accept(v);
        builder.append("</drawing>");
        writer.println(builder.toString());
        writer.close();
    }
}




