package fr.cypno.anthill.map.exceptions;

/**
 * InvalidMapFile est la classe représentant une exception générée au niveau de 
 * la conception de la carte à partir du fichier map.txt
 * 
 * Il peut s'agir de : 
 * - Nombre de lignes incorrect
 * - Nombre de colonnes incorrect
 * - Nombre de sources de nourriture incorrect
 * - Une quantité de nourriture invalide (le nombre de sources ne correspond pas
 * au nombre de quantités de nourriture)
 * - Une ligne de la carte dont la longueur ne correspond pas au nombre de colonnes
 * - Nombre de fourmilière incorrect (différent de 1)
 * - Caractère invalide (différent de ' ', 'x', 'o' et '#')
 * - Carte non fermée par des obstacles
 */

public class InvalidMapFile extends Exception {

    /**
     * Constructeur de la classe InvalidMapFile
     * 
     * @param message Message personnalisé en fonction de l'exception
     */
    public InvalidMapFile(String message) {
        throw new UnsupportedOperationException(message);
    }
    
}
