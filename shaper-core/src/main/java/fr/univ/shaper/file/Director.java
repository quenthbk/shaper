package fr.univ.shaper.file;

import fr.univ.shaper.core.GraphicBuilder;
import fr.univ.shaper.core.element.Layer;

import java.io.File;
import java.io.IOException;

/**
 * Un directeur permettant de faire des conversions du modéle core vers un fichier
 */
public interface Director {
    /**
     * Charge un fichier
     *
     * POST: getFile() doit être égal à {file}
     *
     * @param file le fichier à charger
     * @param builder le constructeur à utiliser
     * @return Un calque contenant tous les éléments graphiques du fichiers
     * @throws IOException si une erreur est survenue lors de la lecture du fichier
     */
    Layer load(File file, GraphicBuilder builder) throws IOException;

    /**
     * Sauvegarde le model de donnée Shaper dans le fichier getFile()
     *
     * @param element le modéle de donnée à sauvegarder
     * @throws IOException si une erreur d'écriture survient
     */
    void save(Layer element) throws IOException;

    /**
     * Sauvegarde le model de donnée Shaper dans le fichier file.
     *
     * POST: getFile() doit être égal à {file}
     *
     * @param file le fichier dans lequel sera écris le sauvegarder le calque
     * @param element le calque à sauvegarder
     * @throws IOException Si une erreur d'écriture survient
     */
    void saveAs(File file, Layer element) throws IOException;

    /**
     * Indique si un fichier est présent
     *
     * @return true si le fichier est présent
     */
    boolean fileIsPresent();

    /**
     *
     * @return le fichier sauvegardé ou ouvert.
     */
    File getFile();
}
