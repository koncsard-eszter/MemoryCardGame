import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EndPanel extends JPanel {
    private JFrame frame;
    private TimerPanel timerPanel;
    private MovesPanel movesPanel;

    public EndPanel(JFrame frame, TimerPanel timerPanel, MovesPanel movesPanel) throws IOException {
        this.frame = frame;
        this.timerPanel = timerPanel;
        this.movesPanel = movesPanel;
        setLayout(new BorderLayout());
        JButton newGame = new JButton("New game");
        newGame.setPreferredSize(new Dimension(50, 50));
        newGame.setBackground(new Color(200, 201, 239));
        newGame.setForeground(new Color(53, 70, 70));
        add(newGame, BorderLayout.SOUTH);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                try {
                    showGamePanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton menu = new JButton("Menu");
        menu.setPreferredSize(new Dimension(50, 50));
        menu.setBackground(new Color(200, 201, 239));
        menu.setForeground(new Color(53, 70, 70));
        add(menu, BorderLayout.NORTH);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                try {
                    showMenuPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        String wordsFileName = "";
        if (frame instanceof Frame) {
            wordsFileName = ((Frame) frame).getWordsFileName();
        }
        int language_level = 0;
        if (wordsFileName.equals("texts\\firstLevel.txt")) {
            language_level = 1;
        } else if (wordsFileName.equals("texts\\secondLevel.txt")) {
            language_level = 2;
        } else {
            language_level = 3;
        }
        int score;
        if (movesPanel.getMisses() == 0) {
            score = movesPanel.getHits() * 10000 * language_level / timerPanel.getElapsedTime();

        } else {
            score = movesPanel.getHits() * 10000 * language_level/ timerPanel.getElapsedTime() * movesPanel.getMisses();
        }
        JLabel congratulationLabel = new JLabel();
        congratulationLabel.setFont(new Font("Balthazar", Font.BOLD, 40));
        congratulationLabel.setText("CONGRATULATIONS! " + "Your Score is " + score);
        congratulationLabel.setHorizontalAlignment(JLabel.CENTER);
        congratulationLabel.setVerticalAlignment(JLabel.CENTER);
        congratulationLabel.setForeground(new Color(216, 216, 227));
        add(congratulationLabel, BorderLayout.CENTER);

        setBackground(new Color(60, 140, 7));
        String scoresFileName = "";
        if (frame instanceof Frame) {
            scoresFileName = ((Frame) frame).getScoresFileName();
        }
        String playerName = "";
        if (frame instanceof Frame) {
            playerName = ((Frame) frame).getPlayerName();
        }
        writeScoreIfGood(scoresFileName, score, playerName);
    }

    private void showGamePanel() throws IOException {
        GamePanel gamePanel = new GamePanel(frame);
        frame.getContentPane().add(gamePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void showMenuPanel() throws IOException {
        MenuPanel menuPanel = new MenuPanel(frame);
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void writeScoreIfGood(String fileName, int score, String playerName) throws IOException {
        ArrayList<String> scores = new ArrayList<>();
        String tmp = null;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        boolean mustWrite = false;
        while ((tmp = br.readLine()) != null) {
            scores.add(tmp);
        }
        br.close();
        if (scores.size() < 5) {
            mustWrite = true;
        }
        String lowersLine = "";
        int lower = 1000000000;
        if ((new File(fileName)).length() == 0) {
            mustWrite = true;
        }
        if (scores.size() >= 5) {
            for (int i = 0; i < scores.size(); i++) {
                String[] components = scores.get(i).split(": ");
                if (score > Integer.parseInt(components[1])) {
                    if (Integer.parseInt(components[1]) < lower) {
                        lower = Integer.parseInt(components[1]);
                        lowersLine = scores.get(i);
                        mustWrite = true;
                    }
                }
            }
        }
        if (mustWrite) {
            BufferedWriter writer = null;
            try {
                boolean isEntered = false;
                writer = new BufferedWriter(new FileWriter(fileName));
                for (int i = 0; i < scores.size(); i++) {
                    String[] components = scores.get(i).split(": ");
                    if (Integer.parseInt(components[1]) < score && !isEntered) {
                        writer.write(playerName + ": " + score + "\n");
                        isEntered = true;
                    }
                    if (!(scores.get(i).equals(lowersLine))) {
                        writer.write(scores.get(i) + "\n");
                    }
                }
                if (scores.size() == 0) {
                    writer.write(playerName + ": " + score + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                {
                    writer.close();
                }
            }
        }
    }
}
