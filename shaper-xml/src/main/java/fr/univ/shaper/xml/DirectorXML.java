package fr.univ.shaper.xml;


import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.GraphicFactoryHandler;
import fr.univ.shaper.core.element.Layer;
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

    private final static boolean USE_DTD = false;

    private final Charset charset = StandardCharsets.UTF_8;

    private File file;

    private final SAXParserFactory saxFactory;

    public DirectorXML() {
        saxFactory =  SAXParserFactory.newInstance();
        saxFactory.setValidating(USE_DTD);
    }

    @Override
    public boolean fileIsPresent() {
        return file != null;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void save(Layer element) throws IOException {
        if (! fileIsPresent()) {
            throw new FileNotFoundException("Aucun fichier n'a été sauvegardé ou ouvert, utilisez la méthode saveAs");
        }
        saveAs(file, element);
    }

    public Layer load(File file, GraphicBuilder builder) throws IOException {
        this.file = file;

        // Chargement de la source
        InputSource is = new InputSource(
                new BufferedInputStream(
                        new FileInputStream(file.getAbsolutePath())
                )
        );

        // Récupération d'un parser SAX.
        SAXParser sp;
        try {
            sp = saxFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Une erreur de configuration est survenu lors de l'instanciation" +
                    " d'un parser SAX.", e);
        }



        // Récupération d'un lecteur XML
        XMLReader xr;
        try {
            xr = sp.getXMLReader();
        } catch (SAXException e) {
            throw new RuntimeException("Une erreur est survenu lors de la récupération d'une instance de " +
                    "XMLReader()", e);
        }



        // Gestionnaire du lecteur XML
        DrawingHandler handler = new DrawingHandler(builder);
        xr.setContentHandler(handler) ;
        xr.setErrorHandler(handler) ;

        NoisyGraphicFactory ngf;
        try {
            ngf = (NoisyGraphicFactory) GraphicFactoryHandler
                    .newInstance().getFactoryOf("Noisy");
            ngf.setGenerateNoise(false);
        } catch (GraphicTypeNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("Loading file...");
            xr.parse(is) ;
        } catch (SAXException e) {
            throw new IOException("Le directeur n'a pas pu lire le fichier.\n" +
                    e.getMessage(), e);
        } finally {
            ngf.setGenerateNoise(true);
        }

        System.out.println("File loaded.");
        return handler.getDrawing();
    }

    @Override
    public void saveAs(File file, Layer layerRoot) throws IOException {
        Contract.assertThat(file != null, "Filename ne doit pas être null");
        Contract.assertThat(layerRoot != null, "element ne doit pas être null");
        this.file = file;

        PrintWriter writer;
        try {
            writer = new PrintWriter(file, charset.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Erreur d'encodage.", e);
        }

        // ----------------------------------------------- //
        //                      HEAD                       //
        // ----------------------------------------------- //

        System.out.println("Saving file...");
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"")
                .append(charset)
                .append("\"?>\n");

        if (USE_DTD) {
            builder.append("<!DOCTYPE drawing SYSTEM \"drawing.dtd\">\n");
        }

        builder.append("<drawing xmlns=\"http://www.univ-rouen.fr/drawing\" ")
                .append("width=\"")
                .append(layerRoot.getWidth())
                .append("\" height=\"")
                .append(layerRoot.getHeight())
                .append("\">\n");
        XmlGraphicVisitor v = new XmlGraphicVisitor(builder);
        layerRoot.getChildren().forEach(child -> child.accept(v));
        builder.append("</drawing>");
        writer.println(builder.toString());
        writer.close();
        System.out.println("File Saved.");
    }
}




