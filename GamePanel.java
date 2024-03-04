import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {
    private final JFrame frame;
    public GamePanel(JFrame frame) throws IOException {
        setBackground(new Color(56, 159, 25));
        this.frame=frame;
        MovesPanel movesPanel=new MovesPanel();
        setLayout(new BorderLayout());

        SoundControl soundControl=new SoundControl(frame,movesPanel);
        Thread thread1=new Thread(soundControl);
        thread1.start();

        TimerPanel time = new TimerPanel();
        TimerControl control= new TimerControl(time,movesPanel,frame);
        Thread thread2=new Thread(control);
        thread2.start();

        add(time,BorderLayout.NORTH);
        CardPanel cardPanel=new CardPanel(frame,movesPanel);
        add(cardPanel,BorderLayout.CENTER);
        add(movesPanel,BorderLayout.SOUTH);
    }
}
