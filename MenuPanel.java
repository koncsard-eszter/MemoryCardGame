import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private final JFrame frame;

    public MenuPanel(JFrame frame) {
        this.frame = frame;
        setBackground(new Color(56, 159, 25));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Memory Card Game");
        title.setFont(new Font("Balthazar", Font.BOLD, 40));
        add(title, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttons = new JPanel(new GridBagLayout());

        buttons.setBackground(new Color(56, 159, 25));
        JButton start = new JButton("Start game");
        JButton scores = new JButton("Scores");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");

        start.setPreferredSize(new Dimension(200, 100));
        scores.setPreferredSize(new Dimension(200, 100));
        help.setPreferredSize(new Dimension(200, 100));
        exit.setPreferredSize(new Dimension(200, 100));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                try {
                    showBeforeGamePanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        scores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                try {
                    showScorePanel();
                } catch (IOException ex) {
                    throw new RuntimeException();
                }
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                try {
                    showHelpPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.insets = new Insets(10, 0, 0, 0);

        buttons.add(start, gbc);
        buttons.add(scores, gbc);
        buttons.add(help, gbc);
        buttons.add(exit, gbc);


        gbc.weighty = 1;
        add(buttons, gbc);
    }

    private void showBeforeGamePanel() throws IOException {
        EnterNamePanel beforeGamePanel = new EnterNamePanel(frame);
        frame.getContentPane().add(beforeGamePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void showScorePanel() throws IOException {
        ScorePanel scorePanel = new ScorePanel(frame);
        frame.getContentPane().add(scorePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
    private void showHelpPanel() throws IOException {
        HelpPanel helpPanel = new HelpPanel(frame);
        frame.getContentPane().add(helpPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
