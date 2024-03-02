import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private boolean found;
    private boolean flipped;
    private String word;
    public Card(String word){
        this.found=false;
        this.flipped=false;
        this.word=word;
        setSize(new Dimension(10,20));
        setBackground(new Color(255,255,152));
    }
    public void setFlipped(boolean flipped){
        this.flipped=flipped;
    }
    public void setWord(String word){
        this.word=word;
    }
    public boolean getFlipped(){
        return flipped;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public String getWord(){
        return word;
    }

    public void revealCard(int cardsNumber){
        setText(word);
        if(cardsNumber==12) {
            setFont(new Font("Balthazar", Font.BOLD, 25));
        } else if (cardsNumber==24) {
            setFont(new Font("Balthazar", Font.BOLD, 20));
        } else {
            setFont(new Font("Balthazar", Font.BOLD, 15));
        }
        setBackground(new Color(116, 178, 116));
    }
    public void revealBack(){
        setText("");
        setBackground(new Color(255,255,152));
    }
    public void removeCard(){
        setText("");
        setBackground(Color.WHITE);
        found=true;
    }
}
