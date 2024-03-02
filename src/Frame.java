import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame {
    private String playerName;
    private String wordsFileName;
    private String scoresFileName;
    private int cardsNumber;

    public Frame() {
        wordsFileName = "";
        cardsNumber = 0;
        playerName = "";
        setTitle("Game");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        MenuPanel menuPanel = new MenuPanel(this);
        add(menuPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getWordsFileName() {
        return wordsFileName;
    }

    public int getCardsNumber() {
        return cardsNumber;
    }

    public void setWordsFileName(String wordsFileName) {
        this.wordsFileName = wordsFileName;
    }

    public void setCardsNumber(int cardsNumber) {
        this.cardsNumber = cardsNumber;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getScoresFileName() {
        return scoresFileName;
    }
    public void setScoresFileName(String scoresFileName) {
        this.scoresFileName = scoresFileName;
    }

    public static void main(String[] args) throws IOException {
        new Frame();
    }
}
