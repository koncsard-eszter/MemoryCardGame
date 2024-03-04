import javax.swing.*;

public class MovesPanel extends JPanel{
    private int moves;
    private int misses;
    private int hits;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    public MovesPanel(){
        moves=0;
        hits=0;
        misses=0;
        label1=new JLabel("Moves: "+ moves);
        label2=new JLabel("Hits: "+hits);
        label3=new JLabel("Misses: "+misses);
        add(label1);
        add(label2);
        add(label3);
    }

    public void setLabels(){
        label1.setText("Moves: "+ moves);
        label2.setText("Hits: "+ hits);
        label3.setText("Misses: "+misses);
    }
    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMoves() {
        return moves;
    }

    public int getMisses() {
        return misses;
    }

    public int getHits() {
        return hits;
    }
}
