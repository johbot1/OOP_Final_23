/**
 * @title Song.java
 * @author johnbotonakis (with ChatGPT used for code commenting)
 * @due 12/11/23
 * @desc The Song class encapsulates song details, providing methods 
 *       to access and modify attributes such as title, artist, album, 
 *       and description. It offers functionalities to set and retrieve 
 *       specific song information, serving as a container for managing 
 *       song data within the application.
 */
package project2;

public class Song {

    private String Title;
    private String Artist;
    private String Album;
    private String Description;

    /**
     * Gets the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Sets the title of the song.
     *
     * @param title The title of the song.
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Gets the album of the song.
     *
     * @return The album of the song.
     */
    public String getAlbum() {
        return Album;
    }

    /**
     * Sets the album of the song.
     *
     * @param album The album of the song.
     */
    public void setAlbum(String album) {
        Album = album;
    }

    /**
     * Gets the artist of the song.
     *
     * @return The artist of the song.
     */
    public String getArtist() {
        return Artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist The artist of the song.
     */
    public void setArtist(String artist) {
        Artist = artist;
    }

    /**
     * Gets the description of the song.
     *
     * @return The description of the song.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets the description of the song.
     *
     * @param desc The description of the song.
     */
    public void setDescription(String desc) {
        Description = desc;
    }

    /**
     * Sets the details of a song (title, album, artist, description).
     *
     * @param title  The title of the song.
     * @param album  The album of the song.
     * @param artist The artist of the song.
     * @param desc   The description of the song.
     */
    public void addSong(String title, String album, String artist, String desc) {
        this.Title = title;
        this.Album = album;
        this.Artist = artist;
        this.Description = desc;
    }
}
