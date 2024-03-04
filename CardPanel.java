import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class CardPanel extends JPanel {
    private final JFrame frame;
    private ArrayList<Card> cards;
    private ArrayList<String> words_to_shuffle;
    private Card firstPair;
    private Card secondPair;
    private Map<String, String> words ;

    public CardPanel(JFrame frame, MovesPanel movesPanel) throws IOException {
        words = new HashMap<>();
        String wordsFileName="";
        if (frame instanceof Frame) {
             wordsFileName = ((Frame) frame).getWordsFileName();
        }
        getTheWords(wordsFileName);
        cards = new ArrayList<>();
        words_to_shuffle = new ArrayList<>();
        this.frame = frame;
        firstPair = null;
        secondPair = null;
        setBackground(new Color(191, 180, 255));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        int cardsNumber = 0;
        if (frame instanceof Frame) {
            cardsNumber = ((Frame) frame).getCardsNumber();
        }
        if (cardsNumber == 12) {
            setLayout(new GridLayout(4, 3));
        } else if (cardsNumber == 24) {
            setLayout(new GridLayout(6, 4));
        } else {
            setLayout(new GridLayout(6, 6));
        }
        for (int i = 0; i < cardsNumber; i++) {
            Card card_temp = new Card("");
            cards.add(card_temp);
            add(card_temp);
            int finalCardsNumber = cardsNumber;
            card_temp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean tmp= firstPair == null || firstPair != card_temp;
                    if (!card_temp.isFound() && !(firstPair != null && secondPair != null) && tmp) {
                        card_temp.revealCard(finalCardsNumber);
                        if (firstPair == null) {
                            firstPair = card_temp;
                        } else if (secondPair == null) {
                            secondPair = card_temp;
                            boolean arePairsVar = false;
                            if (arePairs()) {
                                arePairsVar = true;
                                movesPanel.setHits(movesPanel.getHits() + 1);
                                try {
                                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\success.wav"));
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioInputStream);
                                    clip.start();
                                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException x) {
                                    x.printStackTrace();
                                }
                            } else {
                                try {
                                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\negative_beeps.wav"));
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioInputStream);
                                    clip.start();
                                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException x) {
                                    x.printStackTrace();
                                }
                                movesPanel.setMisses(movesPanel.getMisses() + 1);
                            }
                            movesPanel.setMoves(movesPanel.getMoves() + 1);
                            movesPanel.setLabels();

                            boolean finalArePairsVar = arePairsVar; // miert hiba ha nem csinalok rola masolatot?
                            Timer timer = new Timer(2000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    firstPair.revealBack();
                                    secondPair.revealBack();
                                    if (finalArePairsVar) {
                                        firstPair.removeCard();
                                        secondPair.removeCard();
                                    }
                                    firstPair = null;
                                    secondPair = null;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();


                        }
                    }
                }
            });
        }
        shuffle();
        for (int i = 0; i < cardsNumber; i++) {
            cards.get(i).setWord(words_to_shuffle.get(i));
        }
    }

    private boolean arePairs() {
        if (words.containsKey(firstPair.getWord())) {
            if (words.get(firstPair.getWord()).equals(secondPair.getWord())) {
                return true;
            }
        }
        if (words.containsKey(secondPair.getWord())) {
            if (words.get(secondPair.getWord()).equals(firstPair.getWord())) {
                return true;
            }
        }
        return false;
    }

    private void getTheWords(String fileName) throws IOException {
        String tmp="";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((tmp = br.readLine()) != null) {
            String[] components = tmp.split("-");
            words.put(components[0].trim(), components[1].trim());
        }
    }

    private void shuffle() {
        List<String> listOfKeys = words.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        //listOfKeys.forEach(System.out::println);
        Boolean[] choosen = new Boolean[listOfKeys.size()];
        Arrays.fill(choosen, Boolean.FALSE);
        Random random = new Random();
        int n = listOfKeys.size();
        int cardsNumber = 0;
        if (frame instanceof Frame) {
            cardsNumber = ((Frame) frame).getCardsNumber();
        }
        for (int i = 0; i < cardsNumber/2; i++) {
            int rand = random.nextInt(n);
            while (choosen[rand]) {
                rand = random.nextInt(n);
            }
            choosen[rand] = true;
            words_to_shuffle.add(listOfKeys.get(rand));
            words_to_shuffle.add(words.get(listOfKeys.get(rand)));
        }
        Collections.shuffle(words_to_shuffle);

    }
}
