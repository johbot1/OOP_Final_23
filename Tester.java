/**
 * @title Tester.java
 * @author johnbotonakis
 * @due 11/20/23
 * @desc The Tester class conducts rigorous tests on Song and SongMap classes alongside 
 *       the MergeSort utility, validating song management, map functionality, and sorting 
 *       algorithms for accurate and reliable functionality within the project.
 */
package project2;

import javax.swing.ImageIcon;

public class Tester {
    private static int failures = 0;

    public static void main(String[] args) {
//SONG OBJECT TESTS
        // Set up songs
        Song song1 = new Song();
        Song song2 = new Song();
        song1.addSong("Song Title 1", "Album 1", "Artist 1", "Description 1");
        song2.addSong("Song Title 2", "Album 2", "Artist 2", "Description 2");

        // Test getTitle method
        if (!song1.getTitle().equals("Song Title 1") || !song2.getTitle().equals("Song Title 2")) {
            failures++;
            System.err.println("ERROR [GETTITLE]: Incorrect title for Song.");
        }

        // Test getArtist method
        if (!song1.getArtist().equals("Artist 1") || !song2.getArtist().equals("Artist 2")) {
            failures++;
            System.err.println("ERROR [GETARTIST]: Incorrect artist for Song.");
        }

        // Test getAlbum method
        if (!song1.getAlbum().equals("Album 1") || !song2.getAlbum().equals("Album 2")) {
            failures++;
            System.err.println("ERROR [GETALBUM]: Incorrect album for Song.");
        }

        // Test getDescription method
        if (!song1.getDescription().equals("Description 1") || !song2.getDescription().equals("Description 2")) {
            failures++;
            System.err.println("ERROR [GETDESCRIPTION]: Incorrect description for Song.");
        }

        // Test addSong method
        if (!song1.getTitle().equals("Song Title 1") || !song2.getAlbum().equals("Album 2")) {
            failures++;
            System.err.println("ERROR [ADDSONG]: Adding song details failed.");
        }

//SONG MAP TESTS
        // Set up Song Map
        SongMap songMap = new SongMap();

        // Test addSong method
        songMap.addSong(song1);
        songMap.addSong(song2);

        // Test containsKey method
        if (!songMap.containsKey("Song Title 1") || !songMap.containsKey("Song Title 2")) {
            failures++;
            System.err.println("ERROR [CONTAINSKEY]: Key not found in SongMap.");
        }

        // Test returnSong method
        if (!songMap.returnSong("Song Title 1").getTitle().equals("Song Title 1")
                || !songMap.returnSong("Song Title 2").getAlbum().equals("Album 2")) {
            failures++;
            System.err.println("ERROR [RETURNSONG]: Incorrect song retrieved from SongMap.");
        }

        // Test size method
        if (songMap.size() != 2) {
            failures++;
            System.err.println("ERROR [SIZE]: Incorrect size of SongMap.");
        }

        // Test removeSong method
        songMap.removeSong("Song Title 1");
        if (songMap.containsKey("Song Title 1")) {
            failures++;
            System.err.println("ERROR [REMOVESONG]: Song not removed from SongMap.");
        }

        // Test getAlbumImage method
        ImageIcon albumImage = songMap.getAlbumImage("Album 2");
        if (albumImage == null) {
            failures++;
            System.err.println("ERROR [GETALBUMIMAGE]: Image for Album 2 not found.");
        }

//MERGE SORT TESTS
        // Set up a similar test array
        String[] testArray = { "Nightmare", "No Rain", "Crush", "Day 'n' Night", "Bullet" };

        // Test sort method
        MergeSort.Sort(testArray, testArray.length);

        String[] expectedArray = { "Bullet", "Crush", "Day 'n' Night", "Nightmare", "No Rain" };
        for (int i = 0; i < testArray.length; i++) {
            if (!testArray[i].equals(expectedArray[i])) {
                failures++;
                System.err.println("ERROR [SORT]: Array not sorted correctly.");
                break;
            }
        }

        // RESULTS
        if (failures == 0) {
            System.out.println("Woo! All tests passed!");
        } else {
            System.out.println(failures + " tests failed.");
        }
    }
}
