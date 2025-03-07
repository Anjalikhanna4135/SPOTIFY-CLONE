import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class API_PANEL extends JPanel {
    Timer timer;

    JButton playPauseButton;
    JButton nextSongButton;
    JButton previousSongButton;

    BufferedImage songPhoto;

    List<Song> songsList = new ArrayList<>();
    int currentSongIndex = 0; 

    API_PANEL() {
        setSize(500, 500);
        API_CLIENT apiHandler = new API_CLIENT();
        apiHandler.fetchTrackNames();
        songsList = apiHandler.getSongsList(); 

        previousSongButton = new JButton("Previous");
        playPauseButton = new JButton("Play/Pause");
        nextSongButton = new JButton("Next");

        add(previousSongButton);
        add(playPauseButton);
        add(nextSongButton);

        appLoop();

        nextSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextSong();
            }
        });

        playPauseButton.addActionListener(new ActionListener() {
            private boolean isPlaying = false;
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    isPlaying = false;
                    playPauseButton.setText("Play");
                } else {
                    isPlaying = true;
                    playPauseButton.setText("Pause");
                }
            }
        });

        previousSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousSong();
            }
        });

        setFocusable(true);
    }

    void appLoop() {
        timer = new Timer(80, (abc) -> {
            repaint(); 
        });
        timer.start();
    }

    void nextSong() {
        if (currentSongIndex < songsList.size() - 1) {
            currentSongIndex++; 
        } else {
            currentSongIndex = 0; 
        }
    }

    void previousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--; 
        } else {
            currentSongIndex = songsList.size() - 1; 
        }
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);

        pen.setColor(Color.RED);
        pen.setFont(new Font("Arial", Font.BOLD, 25));
        Song currentSong = songsList.get(currentSongIndex);
        pen.drawString(currentSong.getTrackName(), 120, 380);
        pen.drawString(currentSong.getArtistName(),120, 400); 
        pen.drawString(currentSong.getSongUrl(), 150, 420); 
        try {
            songPhoto = ImageIO.read(new URL(currentSong.getImageUrl()));
            pen.drawImage(songPhoto, 120, 100, 250, 250, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}