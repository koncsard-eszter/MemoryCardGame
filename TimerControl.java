import javax.swing.*;
import java.io.IOException;

public class TimerControl implements Runnable{
    private TimerPanel view;
    private MovesPanel movesPanel;
    private JFrame frame;
    public TimerControl(TimerPanel view,MovesPanel movesPanel,JFrame frame){
        this.view=view;
        this.movesPanel=movesPanel;
        this.frame=frame;
    }
    @Override
    public void run() {
        int cardsNumber = 0;
        if (frame instanceof Frame) {
            cardsNumber = ((Frame) frame).getCardsNumber();
        }
        while(movesPanel.getHits()!=cardsNumber/2){
            SwingUtilities.invokeLater(()->{
                view.setElapsedTime(view.getElapsedTime()+1);
                view.setText();
            });
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Error");
            }
        }

        frame.getContentPane().removeAll();
        try {
            showEndPanel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showEndPanel() throws IOException {
        EndPanel endPanel=new EndPanel(frame,view,movesPanel);
        frame.getContentPane().add(endPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
