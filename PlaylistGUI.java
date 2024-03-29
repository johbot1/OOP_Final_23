/**
 * @title PlaylistGUI
 * @author johnbotonakis (with ChatGPT used for code commenting)
 * @help With help from previous labs (Lab10)
 * @due 12/11/23
 * @desc The PlaylistGUI class constructs a graphical user interface (GUI) 
 *       for an Infinite Playlist application. It displays song details, 
 *       allows adding, removing, and editing songs, and provides a visually 
 *       interactive way to manage a music playlist.
 * 
 * @help Skeleton GUI created with the help of the fantastic GuiGenie //Generated by GuiGenie - Copyright (c) 2004 Mario Awad. Home Page http://guigenie.cjb.net - Check often for new versions!
 */

package project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PlaylistGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage backgroundImage;
    private JLabel lblSearch;
    private JLabel lblBSides;
    private JLabel albumArt;
    private JLabel lblSongArtist;
    private JLabel lblAlbum;
    private JLabel lblPlaylist;
    private JLabel lblTitle;
    private JLabel lblClickedTitle;
    private JLabel lblClickedAlbum;
    private JLabel lblClickedArtist;

    private JTextField txtTitle;

    private JButton btnAdd;
    private JButton btnRefresh;
    private JButton btnRemove;
    private JButton btnEdit;

    private JTextArea txtBSides;
    private JList<String> listPlaylist;
    private JScrollPane scrollPane;
    private static String[] playlist;
    private Font labelFont;
    private Song selectedSong;
    String imagePath;

    public PlaylistGUI() {
        // adjust size and set layout
        setPreferredSize(new Dimension(1000, 600));
        setLayout(null);

        // set background
        try {
            backgroundImage = ImageIO.read(new File("src/project2/resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a song map
        SongMap smInfinitePlaylist = HelperFunctions.createSongMap();

        // construct playlist
        playlist = HelperFunctions.loadPlaylist(smInfinitePlaylist);

        // load the correct font
        labelFont = HelperFunctions.loadFont("src/project2/resources/Cotton Cloud.ttf", 40);

        // construct components
        lblTitle = new JLabel("John And Lindsey's Infinite Playlist");
        lblTitle.setFont(labelFont);
        
        lblTitle.setForeground(Color.BLACK);
        lblSearch = new JLabel("Search:");
        lblSearch.setFont(labelFont);
        lblBSides = new JLabel("B-Sides");
        lblBSides.setFont(labelFont);
        lblSongArtist = new JLabel("Song - Artist:");
        lblSongArtist.setFont(labelFont);
        lblClickedTitle = new JLabel("");
        lblClickedArtist = new JLabel("");
        lblAlbum = new JLabel("Album:");
        lblAlbum.setFont(labelFont);
        lblClickedAlbum = new JLabel("");
        lblPlaylist = new JLabel("Playlist:");
        lblPlaylist.setFont(labelFont);

        txtTitle = new JTextField(7);
        txtTitle.setText("Title");
        txtBSides = new JTextArea(5, 5);
        txtBSides.setEditable(false);
        
        btnAdd = new JButton("Add");
        btnRefresh = new JButton("Refresh");
        btnRemove = new JButton("Remove");
        btnEdit = new JButton("Edit");

        // construct album image and image holder
        String imagePath = "src/project2/resources/standard.png";
        int width = 180;
        int height = 200;
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        albumArt = new JLabel(scaledIcon);
        albumArt.setBounds(210, 85, width, height);
        listPlaylist = new JList<>(playlist);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(listPlaylist);

        // add components
        add(lblBSides);
        add(lblSongArtist);
        add(lblAlbum);
        add(lblPlaylist);
        add(lblTitle);
        add(lblSearch);
        add(lblClickedTitle);
        add(lblClickedArtist);
        add(lblClickedAlbum);

        add(txtTitle);
        add(txtBSides);

        add(btnAdd);
        add(btnRefresh);
        add(btnRemove);
        add(btnEdit);
        add(albumArt);
        add(scrollPane);

        //Component placements for a large window size (1000x600)
        //Originally done by GuiGenie but once I upscaled the size, I had
        //to redo alot of these
        lblTitle.setBounds(100, 25, 900, 50);
        lblSearch.setBounds(35, 85, 200, 50);
        lblPlaylist.setBounds(750, 85, 200, 50);
        lblBSides.setBounds(220, 360, 150, 35);

        lblSongArtist.setBounds(430, 95, 270, 50);
        lblClickedTitle.setBounds(430, 140, 220, 25);
        lblClickedAlbum.setBounds(430, 170, 200, 25);

        lblAlbum.setBounds(430, 220, 200, 50);
        lblClickedArtist.setBounds(430, 260, 235, 25);

        txtTitle.setBounds(35, 150, 170, 25);
        txtBSides.setBounds(220, 400, 505, 100);

        btnAdd.setBounds(35, 200, 76, 25);
        btnEdit.setBounds(125,200,76,25);
        btnRemove.setBounds(35, 250, 76, 25);
        btnRefresh.setBounds(125, 250, 76, 25);
        
        albumArt.setBounds(210, 85, 220, 240);
        scrollPane.setBounds(750, 140, 230, 415);
        //Controls update when clicking on an item in the playlist scroll bar
        HelperFunctions.updatePlaylist(txtTitle, playlist, listPlaylist, scrollPane);

        // action listeners
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame("Add Song");
                newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                newFrame.setSize(500, 320);
                JPanel panel = HelperFunctions.addSong(smInfinitePlaylist);

                newFrame.getContentPane().add(panel);
                newFrame.setAlwaysOnTop(true);
                newFrame.setVisible(true);
                JOptionPane.showMessageDialog(null,
                        "To add your song, fill out the information. \nYOUR ART MUST BE THE SAME EXACT NAME AS YOUR ALBUM AND SAVED AS A PNG!",
                        "Adding Instructions", JOptionPane.PLAIN_MESSAGE);
            }
        });

        
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = listPlaylist.getSelectedValue();
                if (selected != null) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this song:\n"+ selected, "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        String[] updatedPlaylist = HelperFunctions.loadPlaylist(smInfinitePlaylist);
                        for (String song : updatedPlaylist) {
                            if (song.equals(selected)) {
                                smInfinitePlaylist.removeSong(selected);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "To remove a song, please highlight the one you want to delete using the Playlist to the right", "Removing Instructions", JOptionPane.PLAIN_MESSAGE);
                }
                HelperFunctions.refresh(listPlaylist, smInfinitePlaylist, scrollPane);
            }


        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = listPlaylist.getSelectedValue();
                if (selected != null) {
                    JFrame newFrame = new JFrame("Edit Song");
                    newFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    newFrame.setSize(500, 320);
                    JPanel panel = HelperFunctions.editSong(selected,smInfinitePlaylist);
                    newFrame.getContentPane().add(panel);
                    newFrame.setAlwaysOnTop(true);
                    newFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "To edit a song, please highlight the one you want to edit using the Playlist to the right", "Editing Instructions", JOptionPane.PLAIN_MESSAGE);
                }
                HelperFunctions.refresh(listPlaylist, smInfinitePlaylist, scrollPane);
            }


        });
        
        //Refresh the playlist once hit
        //Good for after adding in songs
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelperFunctions.refresh(listPlaylist, smInfinitePlaylist, scrollPane);
                albumArt.revalidate();
                albumArt.repaint();
            }});
        
        //Playlist selection and box filling
        listPlaylist.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // To prevent multiple events on a single selection
                if (listPlaylist.getSelectedValue() != null) {
                    String selectedTitle = (String) listPlaylist.getSelectedValue(); // Get the selected song title
                    
                    // Assuming getSongByTitle() is a method in SongMap to retrieve a song by its title
                    selectedSong = smInfinitePlaylist.returnSong(selectedTitle);
                    
                    // Populate text fields with information from the selected song
                    lblClickedTitle.setText(selectedSong.getTitle());
                    lblClickedArtist.setText(selectedSong.getArtist());
                    lblClickedAlbum.setText(selectedSong.getAlbum());
                    
                    txtBSides.setText(selectedSong.getDescription());
                    txtBSides.setWrapStyleWord(true);
                    txtBSides.setLineWrap(true);
                    txtBSides.selectAll();
                    
                    ImageIcon albumImageIcon = smInfinitePlaylist.getAlbumImage(selectedSong.getArtist());
                    
                    if (albumImageIcon != null) {
                        Image image = albumImageIcon.getImage();
                        Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        ImageIcon finalIcon = new ImageIcon(scaled);
                        albumArt.setIcon(finalIcon);
                    }
                    
                }
                    
                }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PlaylistGUI());
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null,"Welcome to the Infinite Playlist! This is a collection of various songs that made an impact in some way or another. Feel free to add, remove, or edit any of the songs you see here.", "Hello!",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
