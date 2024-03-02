import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    private int elapsedTime;
    private JLabel label;
    public TimerPanel(){
        this.elapsedTime=0;
        label=new JLabel();
        label.setFont(new Font("Arial",Font.BOLD,30));
        add(label);
    }
    public int getElapsedTime(){
        return elapsedTime;
    }
    public void setElapsedTime(int elapsedTime){
        this.elapsedTime=elapsedTime;
    }
    public void setText(){
        int minute=elapsedTime/60;
        int mp=elapsedTime%60;
        label.setText(minute+":"+mp);
    }
}
