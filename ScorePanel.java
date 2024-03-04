import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScorePanel extends JPanel{
    private final JFrame frame;
    private GridBagConstraints gbc;
    private Font textFont;
    public ScorePanel(JFrame frame) throws IOException {
        this.frame=frame;
        setBackground(new Color(56, 159, 25));
        Insets insets1=new Insets(5,0,0,0);
        Insets insets2=new Insets(13,0,0,0);
        String scoresFileName="";
        if (frame instanceof Frame) {
            scoresFileName = ((Frame) frame).getScoresFileName();
        }
        setLayout(new GridBagLayout());
        gbc=new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;//a komponens a sor vegeig terjed es a kov komponens egy uj sorba kerul
        gbc.anchor = GridBagConstraints.CENTER; //az anchor megadja hogy a komponens hogy igazodik el az ures teruleten belul, ha a racsban tobb hely all rendelkezesre mint amennyire a komponensnek szuksege van
        gbc.insets=insets2;

        JButton back=new JButton("BACK");
        back.setPreferredSize(new Dimension(80,60));

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                showMenuPanel();
            }
        });
        add(back,gbc);

        Font labelsFont=new Font("Balthazar",Font.PLAIN,30);

        JLabel firstLevel=new JLabel("EASY ~ 12 cards");
        firstLevel.setFont(labelsFont);
        JLabel secondLevel=new JLabel("MEDIUM ~ 24 cards");
        secondLevel.setFont(labelsFont);
        JLabel thirdLevel=new JLabel("HARD ~ 36 cards");
        thirdLevel.setFont(labelsFont);

        textFont=new Font("Balthazar",Font.PLAIN,20);

        gbc.insets=insets2;
        add(firstLevel,gbc);
        gbc.insets=insets1;
        readTextFromFile("scores\\firstLevelScores");

        gbc.insets=insets2;
        add(secondLevel,gbc);
        gbc.insets=insets1;
        readTextFromFile("scores\\secondLevelScores");

        gbc.insets=insets2;
        add(thirdLevel,gbc);
        gbc.insets=insets1;
        readTextFromFile("scores\\thirdLevelScores");

    }
    public void readTextFromFile(String fileName) throws IOException {
        BufferedReader reader1=new BufferedReader(new FileReader(fileName));
        String tmp="";
        boolean areLines=false;
        while((tmp=reader1.readLine())!=null){
            JLabel tmpLabel=new JLabel(tmp);
            tmpLabel.setFont(textFont);
            add(tmpLabel,gbc);
            areLines=true;
        }
        if(!areLines){
            JLabel tmpLabel=new JLabel("-------------------");
            tmpLabel.setFont(textFont);
            add(tmpLabel,gbc);
        }

    }
    private void showMenuPanel(){
        MenuPanel menuPanel=new MenuPanel(frame);
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}

