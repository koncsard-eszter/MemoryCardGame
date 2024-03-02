import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class SetCardsNumber extends JPanel {
    private JFrame frame;
    GridBagConstraints gbc;

    public SetCardsNumber(JFrame frame) {
        setBackground(new Color(56, 159, 25));
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 0, 0, 0);
        this.frame = frame;
        JLabel text2 = new JLabel("Please select the number of the cards");
        text2.setFont(new Font("Balthazar", Font.BOLD, 20));
        JButton c12 = new JButton("12");
        c12.setPreferredSize(new Dimension(100, 40));
        JButton c24 = new JButton("24");
        c24.setPreferredSize(new Dimension(100, 40));
        JButton c36 = new JButton("36");
        c36.setPreferredSize(new Dimension(100, 40));
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 4));
        buttons.add(c12);
        buttons.add(c24);
        buttons.add(c36);
        add(text2, gbc);
        add(buttons, gbc);
        c12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setCardsNumber(12);
                    ((Frame) frame).setScoresFileName("scores\\firstLevelScores");
                }
                goToPlayButton();
            }
        });
        c24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setCardsNumber(24);
                    ((Frame) frame).setScoresFileName("scores\\secondLevelScores");
                }
                goToPlayButton();
            }
        });
        c36.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setCardsNumber(36);
                    ((Frame) frame).setScoresFileName("scores\\thirdLevelScores");
                }
                goToPlayButton();
            }
        });
    }
    private void goToPlayButton(){
        JButton goAndPlay = new JButton("GO TO PLAY");
        goAndPlay.setPreferredSize(new Dimension(100, 40));
        goAndPlay.setBackground(Color.green);
        add(goAndPlay,gbc);
        revalidate();
        repaint();
        goAndPlay.setPreferredSize(new Dimension(200, 40));
        goAndPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showGamePanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void showHelpPanel() throws IOException {
        frame.getContentPane().removeAll();
        HelpPanel helpPanel = new HelpPanel(frame);
        frame.getContentPane().add(helpPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void showGamePanel() throws IOException {
        frame.getContentPane().removeAll();
        GamePanel gamePanel = new GamePanel(frame);
        frame.getContentPane().add(gamePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}

