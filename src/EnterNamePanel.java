import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNamePanel extends JPanel {
    private JFrame frame;
    public EnterNamePanel(JFrame frame){
        setLayout(new GridBagLayout());
        this.frame=frame;
        setBackground(new Color(56, 159, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets=new Insets(15,0,0,0);
        JLabel enterName=new JLabel("ENTER YOUR NAME");
        enterName.setFont(new Font("Balthazar",Font.BOLD,40));
        JTextArea name=new JTextArea();
        name.setPreferredSize(new Dimension(200,52));
        name.setFont(new Font("Balthazar", Font.BOLD,30));
        name.setBackground(Color.WHITE);
        JButton readyName=new JButton ("✓");
        readyName.setPreferredSize(new Dimension(70,40));
        readyName.setBackground(Color.green);
        JLabel text=new JLabel("Please press the pipe button,after entering your name");
        text.setFont(new Font("Balthazar",Font.BOLD,20));
        add(enterName,gbc);
        add(name,gbc);
        add(text,gbc);
        add(readyName,gbc);
        readyName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame instanceof Frame) {
                    ((Frame) frame).setPlayerName(name.getText());
                }
                frame.getContentPane().removeAll();
                showSetThingsPanel();
            }
        });

    }
    private void showSetThingsPanel (){
        SetLevelPanel setThingsPanel=new SetLevelPanel(frame);
        frame.getContentPane().add(setThingsPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
