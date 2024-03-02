import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SoundControl implements Runnable{
    private MovesPanel movesPanel;
    private Timer timer;
    private Clip clip;
    private JFrame frame;
    public SoundControl(JFrame frame,MovesPanel movesPanel){
        this.frame=frame;
        this.movesPanel=movesPanel;
        this.timer=new Timer(120000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playBackgroungMusic();
            }
        });
    }

    @Override
    public void run() {
        playBackgroungMusic();
        timer.start();
        int cardsNumber = 0;
        if (frame instanceof Frame) {
            cardsNumber = ((Frame) frame).getCardsNumber();
        }
        while(movesPanel.getHits()<cardsNumber/2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread error");
            }
        }
        clip.stop();
        timer.stop();
    }
    private void playBackgroungMusic(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\background.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException| IOException e){
            e.printStackTrace();
        }
    }
}
