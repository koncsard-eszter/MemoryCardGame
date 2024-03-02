import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetLevelPanel extends JPanel {
    private JFrame frame;
    public SetLevelPanel(JFrame frame){
        setBackground(new Color(56, 159, 25));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(15,0,0,0);
        this.frame=frame;
        JLabel text2=new JLabel("Please select the level, which you want to practice");
        text2.setFont(new Font("Balthazar",Font.BOLD,20));
        gbc.insets=new Insets(25,0,0,0);
        add(text2,gbc);
        gbc.insets=new Insets(15,0,0,0);
        JPanel levelPanel=new JPanel();
        JButton A1_A2=new JButton("A1-A2");
        JButton B1_B2=new JButton("B1-B2");
        JButton C1_C2=new JButton("C1-C2");
        levelPanel.setLayout(new GridLayout(1,4));
        levelPanel.add(A1_A2);
        levelPanel.add(B1_B2);
        levelPanel.add(C1_C2);
        add(levelPanel,gbc);
        A1_A2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setWordsFileName("texts\\firstLevel.txt");
                }
               showSetCardsNumberPanel();
            }
        });
        B1_B2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setWordsFileName("texts\\secondLevel.txt");
                }
               showSetCardsNumberPanel();
            }
        });
        C1_C2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setWordsFileName("texts\\thirdLevel.txt");
                }
               showSetCardsNumberPanel();
            }
        });
    }
    private void showSetCardsNumberPanel (){
        frame.getContentPane().removeAll();
        SetCardsNumber setCardsNumber=new SetCardsNumber(frame);
        frame.getContentPane().add(setCardsNumber);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
