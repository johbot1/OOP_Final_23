/**
 * @title SongMap
 * @author johnbotonakis (with ChatGPT used for code commenting)
 * @help With help from previous labs (Lab10)
 * @due 12/11/23
 * @desc The SongMap class manages a mapping of song titles to 
 *       Song objects using a HashMap, providing functionalities 
 *       to add, retrieve, and remove songs, as well as fetch album 
 *       images associated with specific albums stored within the map.
 */
package project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Represents a mapping of song titles to Song objects using a HashMap.
 */
public class SongMap {
    private Map<String, Song> map = new HashMap<>();

    /**
     * Constructs a SongMap with an empty HashMap.
     */
    public SongMap() {
        map = new HashMap<>();
    }

    /**
     * Constructs a SongMap with a HashMap of specified capacity.
     *
     * @param capacity The initial capacity of the SongMap.
     */
    public SongMap(int capacity) {
        map = new HashMap<>(capacity);
    }

    /**
     * Adds a song to the SongMap.
     *
     * @param song The Song object to add to the SongMap.
     */
    public void addSong(Song song) {
        map.put(song.getTitle(), song);
    }

    /**
     * Checks if the SongMap contains a song with a specified title.
     *
     * @param title The title of the song to check for.
     * @return True if the SongMap contains the song, otherwise false.
     */
    public boolean containsKey(String title) {
        return map.containsKey(title);
    }

    /**
     * Retrieves a set of entries in the SongMap.
     *
     * @return A list of Map entries representing songs in the SongMap.
     */
    public List<Map.Entry<String, Song>> entrySet() {
        return new ArrayList<>(map.entrySet());
    }

    /**
     * Returns the Song object associated with the specified title.
     *
     * @param title The title of the song to retrieve.
     * @return The Song object corresponding to the given title.
     */
    public Song returnSong(String title) {
        return map.get(title);
    }

    /**
     * Returns the size of the SongMap.
     *
     * @return The number of songs in the SongMap.
     */
    public int size() {
        return map.size();
    }

    /**
     * Removes a song from the SongMap based on the given title.
     *
     * @param title The title of the song to remove.
     */
    public void removeSong(String title) {
        if (map.containsKey(title)) {
            map.remove(title);
        } else {
            System.err.println("No Song with that title found!");
        }
    }

    /**
     * Retrieves the album image associated with the specified album title.
     *
     * @param album The title of the album to retrieve the image for.
     * @return An ImageIcon representing the album image.
     */
    public ImageIcon getAlbumImage(String album) {
        String imagePath = "src/project2/resources/AlbumArt/" + album + ".png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        return imageIcon;
    }
}
