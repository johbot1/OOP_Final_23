/**
 * @title HelperFunctions.java
 * @author johnbotonakis (with ChatGPT used for code commenting)
 * @due 12/11/23
 * @desc This class, HelperFunctions, contains various utility methods 
 *       that assist in handling tasks such as file operations, GUI component creation, 
 *       and data manipulation, providing essential support functionalities for other 
 *       parts of the application.
 */

package project2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelperFunctions {
    // The folder path where album art resources are stored.
    private static File folder = new File("src/project2/resources/AlbumArt/");

    // The selected file to be used with loading a custom album image
    private static File selectedFile;

    /**
     * Loads a custom font from a file.
     *
     * @param path The path to the font file.
     * @param size The size of the font to be loaded.
     * @return The loaded Font object.
     */
    public static Font loadFont(String path, float size) {
        Font customFont = null;
        try {
            // Load the font file
            File fontFile = new File(path);
            if (!fontFile.canRead()) {
                System.err.println("Font file not accessible.");
                return null;
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }

    /**
     * Loads a playlist from a SongMap and returns an array of song titles sorted in
     * alphabetical order.
     *
     * @param entryMap The SongMap containing song entries.
     * @return A String array representing the playlist with song titles sorted
     *         alphabetically.
     */
    public static String[] loadPlaylist(SongMap entryMap) {
        // Create a String array to hold the playlist
        String[] playlist = new String[entryMap.size()];
        int index = 0;

        // Iterate through the SongMap entries to extract song titles
        for (Map.Entry<String, Song> entry : entryMap.entrySet()) {
            Song song = entry.getValue();
            String title = song.getTitle();

            // Add the song title to the playlist array
            playlist[index] = title;
            index++;
        }

        // Sort the playlist array using the MergeSort algorithm
        MergeSort.Sort(playlist, playlist.length);

        // Return the sorted playlist
        return playlist;
    }

    /**
     * Creates a SongMap by reading song details from a file and adding them to the
     * map.
     *
     * @return A SongMap containing songs read from a file.
     */
    public static SongMap createSongMap() {
        // Create a new SongMap instance
        SongMap smInfinitePlaylist = new SongMap();
        final String filename = "Songs.txt";

        try (final Scanner inputScanner = new Scanner(new File("src/project2/" + filename))) {
            // Read the file line by line
            while (inputScanner.hasNextLine()) {
                final String line = inputScanner.nextLine();
                String[] parts = line.split(";");

                // Create a new Song object and add song details
                Song newSong = new Song();
                newSong.addSong(parts[0], parts[1], parts[2], parts[3]);

                // Add the song to the SongMap
                smInfinitePlaylist.addSong(newSong);
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.err.println("File could not be found. Check to ensure that it is in the correct location.");
            e.printStackTrace();
        }

        // Return the populated SongMap
        return smInfinitePlaylist;
    }

    
     public static JPanel addSong(SongMap smInfinitePlaylist) {
        JPanel panel = new JPanel();

        //construct components
        JLabel lblAddTitle = new JLabel ("Title");
        JTextField addtxtTitle = new JTextField (5);
        JLabel lblAddArtist = new JLabel ("Artist");
        JTextField addtxtArtist = new JTextField (5);
        JLabel lblAddAlbum = new JLabel ("Album");
        JTextField addtxtAlbum = new JTextField (5);
        JLabel lblAddBSides = new JLabel ("B-Side");
        JTextField addtxtBSide = new JTextField (5);
        JButton btnImgArt = new JButton("Choose Image");
        JLabel lblAddImgArt = new JLabel ("Album Art");
        JLabel lblImagePath = new JLabel (" ");
        JButton btnAdd = new JButton ("Add");
        
        //adjust size and set layout
        panel.setPreferredSize (new Dimension (500, 300));
        panel.setLayout (null);

        //add components
        panel.add (lblAddTitle);
        panel.add (lblAddArtist);
        panel.add (lblAddAlbum);
        panel.add(lblImagePath);
        panel.add (lblAddBSides);
        panel.add (addtxtBSide);
        panel.add (lblAddImgArt);
        panel.add (addtxtTitle);
        panel.add (addtxtArtist);
        panel.add (addtxtAlbum);
        panel.add(btnAdd);
        panel.add(btnImgArt);

        //set component bounds
        lblAddTitle.setBounds (15, 25, 100, 25);
        lblAddArtist.setBounds (10, 90, 100, 25);
        lblAddAlbum.setBounds (10, 170, 100, 25);
        lblAddBSides.setBounds (150, 30, 100, 25);
        addtxtBSide.setBounds (145, 55, 250, 65);
        lblAddImgArt.setBounds (145, 140, 100, 25);
        lblImagePath.setBounds (145, 180, 200, 25);
        btnImgArt.setBounds(140, 200, 100, 25);
        addtxtTitle.setBounds (10, 50, 100, 25);
        addtxtArtist.setBounds (10, 120, 100, 25);
        addtxtAlbum.setBounds (10, 205, 100, 25);
        btnAdd.setBounds (350, 250, 100, 25);
        
        btnImgArt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedArt = fileChooser.getSelectedFile();
                   
                    lblImagePath.setText(selectedArt.toString());
                    
                    // Assign the selected file to the class-level variable
                    selectedFile = selectedArt;
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = addtxtTitle.getText();
                String artist =  addtxtArtist.getText();
                String album = addtxtAlbum.getText();
                String bsides = addtxtBSide.getText();
                if(title.isEmpty() || artist.isEmpty()|| album.isEmpty()|| bsides.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Looks like you left some things empty. Fill them in and try again!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                }else {
                    
                    if(selectedFile != null) {
                        // Copy image to new location
                        copyImageToNewLocation(selectedFile, folder);
                    }else {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "You're about to enter in a Song without Album art. Are you sure you want to do this?", "Confirm Add", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            // Create and add the song to the playlist and song map
                            Song newSong = new Song();
                            newSong.addSong(title, album, artist, bsides);
                            smInfinitePlaylist.addSong(newSong);
                            
                            // Write content to the Songs.txt file
                            String content = ("\n"+title + ";" + artist + ";" + album + ";" + bsides); // Add newline for each entry
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/project2/Songs.txt", true))) {
                                writer.write(content);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            
                        }
                    }
                    
                }
            }
            });
                    
        panel.requestFocusInWindow();
        return panel;
    }

     /**
      * Constructs a panel to edit the details of a selected song within the Infinite Playlist.
      * Allows modification of song attributes such as title, artist, album, description, and album art.
      *
      * @param selectedSong        The title of the song to be edited.
      * @param smInfinitePlaylist  The SongMap containing the playlist.
      * @return                    A JPanel providing a graphical interface for editing song details.
      */
     public static JPanel editSong(String selectedSong, SongMap smInfinitePlaylist) {
         // Create a new JPanel to facilitate song editing
         JPanel panel = new JPanel();
         
         // Retrieve the song to be edited from the SongMap
         Song editingSong = smInfinitePlaylist.returnSong(selectedSong);

        // construct components
        JLabel lblAddTitle = new JLabel("Title");
        JTextField addtxtTitle = new JTextField(5);
        addtxtTitle.setText(editingSong.getTitle());
        JLabel lblAddArtist = new JLabel("Artist");
        JTextField addtxtArtist = new JTextField(5);
        addtxtArtist.setText(editingSong.getAlbum());
        JLabel lblAddAlbum = new JLabel("Album");
        JTextField addtxtAlbum = new JTextField(5);
        addtxtAlbum.setText(editingSong.getArtist());
        JLabel lblAddBSides = new JLabel("B-Side");
        JTextField addtxtBSide = new JTextField(5);
        addtxtBSide.setText(editingSong.getDescription());
        JButton btnImgArt = new JButton("Choose Image");
        JLabel lblAddImgArt = new JLabel("Album Art");
        JLabel lblImagePath = new JLabel(" ");
        JButton btnEdit = new JButton("Edit");

        // adjust size and set layout
        panel.setPreferredSize(new Dimension(500, 300));
        panel.setLayout(null);

        // add components
        panel.add(lblAddTitle);
        panel.add(lblAddArtist);
        panel.add(lblAddAlbum);
        panel.add(lblImagePath);
        panel.add(lblAddBSides);
        panel.add(addtxtBSide);
        panel.add(lblAddImgArt);
        panel.add(addtxtTitle);
        panel.add(addtxtArtist);
        panel.add(addtxtAlbum);
        panel.add(btnEdit);
        panel.add(btnImgArt);

        // set component bounds
        lblAddTitle.setBounds(15, 25, 100, 25);
        lblAddArtist.setBounds(10, 90, 100, 25);
        lblAddAlbum.setBounds(10, 170, 100, 25);
        lblAddBSides.setBounds(150, 30, 100, 25);
        addtxtBSide.setBounds(145, 55, 250, 65);
        lblAddImgArt.setBounds(145, 140, 100, 25);
        lblImagePath.setBounds(145, 180, 200, 25);
        btnImgArt.setBounds(140, 200, 100, 25);
        addtxtTitle.setBounds(10, 50, 100, 25);
        addtxtArtist.setBounds(10, 120, 100, 25);
        addtxtAlbum.setBounds(10, 205, 100, 25);
        btnEdit.setBounds(350, 250, 100, 25);

        // Action listener for choosing a picture
        btnImgArt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedArt = fileChooser.getSelectedFile();

                    lblImagePath.setText(selectedArt.toString());

                    // Assign the selected file to the class-level variable
                    selectedFile = selectedArt;
                }
            }
        });

        // action listener for editing the song
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = addtxtTitle.getText();
                String artist = addtxtArtist.getText();
                String album = addtxtAlbum.getText();
                String bsides = addtxtBSide.getText();
                // checks if the fields are empty first
                if (title.isEmpty() || artist.isEmpty() || album.isEmpty() || bsides.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Looks like you left some things empty. Fill them in and try again!", "ERROR!",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to make changes to this song:\n"+ selectedSong, "Confirm Edits", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        Song editedSong = smInfinitePlaylist.returnSong(selectedSong);
                        editedSong.setTitle(title);
                        editedSong.setArtist(album);
                        editedSong.setAlbum(artist);
                        editedSong.setDescription(bsides);
                        smInfinitePlaylist.removeSong(selectedSong);
                        smInfinitePlaylist.addSong(editedSong);
                        }
                    }
                if(selectedFile != null) {
                    // Copy image to new location
                    copyImageToNewLocation(selectedFile, folder);
                }

            }
        });
        //Set Focus to the window
        panel.requestFocusInWindow();
        
        return panel;
    }

     /**
     * Copies an image file to a new location.
     * @help Created with help from (https://www.baeldung.com/java-write-to-file)
     * @help Created with help from (https://docs.oracle.com/javase/8/docs/api/java/io/File.html)
     *
     * @param sourceImage       The File representing the source image to be copied.
     * @param destinationFolder The File representing the destination folder where
     *                          the image will be copied.
     */
    public static void copyImageToNewLocation(File sourceImage, File destinationFolder) {
        // Check if the source image file exists and is a file
        if (!sourceImage.exists() || !sourceImage.isFile()) {
            System.out.println("Source image does not exist or is not a file.");
            return;
        }

        // Check if the destination folder exists; it doesn't, make it
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Create a File object for the copied image in the destination folder
        File copiedImage = new File(destinationFolder, sourceImage.getName());

        try (InputStream in = new FileInputStream(sourceImage); OutputStream out = new FileOutputStream(copiedImage)) {

            // Read bytes from the source image and write them to the destination
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            System.out.println("Image copied successfully to: " + copiedImage.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the playlist displayed in a JList based on a search term entered in a
     * JTextField.
     * @help Created with help from (https://www.docs4dev.com/docs/en/java/java8/tutorials/uiswing-events-documentlistener.html)
     *
     * @param textField  JTextField where the search term is entered.
     * @param playlist   Array of strings representing the playlist.
     * @param list       JList displaying the playlist.
     * @param scrollPane JScrollPane containing the JList.
     */
    public static void updatePlaylist(JTextField textField, String[] playlist, JList<String> list,
            JScrollPane scrollPane) {
        textField.getDocument().addDocumentListener(new DocumentListener() {

            /**
             * Triggered when text is inserted into the document.
             *
             * @param e DocumentEvent representing the insert action.
             */
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            /**
             * Triggered when text is removed from the document.
             *
             * @param e DocumentEvent representing the removal action.
             */
            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            /**
             * Triggered when text is changed in the document.
             *
             * @param e DocumentEvent representing the change action.
             */
            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            /**
             * Filters the playlist based on the entered search term and updates the JList.
             * @help Created with help from (https://stackoverflow.com/questions/13136826/defaultlistmodel-in-java#13137004)
             * @help Created with help from (https://stackoverflow.com/questions/21227947/how-to-update-jlist)
             */
            private void filter() {
                String searchTerm = textField.getText().toLowerCase();
                DefaultListModel<String> model = new DefaultListModel<>();

                // Filtering the playlist based on the search term
                for (String song : playlist) {
                    if (song.toLowerCase().contains(searchTerm)) {
                        model.addElement(song);
                    }
                }

                // Updating the JList model to display the filtered playlist
                list.setModel(model);
            }
        });
    }
    
    /**
     * Refreshes the playlist displayed in a JList with updated content from the given SongMap.
     *
     * @param listPlaylist        The JList displaying the playlist.
     * @param smInfinitePlaylist  The SongMap containing the updated playlist content.
     * @param scrollPane          The JScrollPane associated with the playlist JList.
     */
    public static void refresh(JList<String> listPlaylist, SongMap smInfinitePlaylist, JScrollPane scrollPane) {
        // Load the updated playlist from the SongMap
        String[] updatedPlaylist = HelperFunctions.loadPlaylist(smInfinitePlaylist);

        // Create a DefaultListModel to update the JList content
        DefaultListModel<String> model = new DefaultListModel<>();

        // Add updated songs to the model
        for (String song : updatedPlaylist) {
            model.addElement(song);
        }

        // Set the updated model to the JList
        listPlaylist.setModel(model);

        // Refresh the JScrollPane to reflect changes in the JList content
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    }