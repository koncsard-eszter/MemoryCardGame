import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelpPanel extends JPanel{
    private final JFrame frame;
    public HelpPanel(JFrame frame) throws IOException {
        this.frame=frame;
        setBackground(new Color(56, 159, 25));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets=new Insets(15,0,0,0);

        JButton back=new JButton("BACK");
        back.setFont(new Font("Balthazar",Font.BOLD,15));
        back.setPreferredSize(new Dimension(80,50));

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                showMenuPanel();
            }
        });
        add(back,gbc);

        JTextArea textArea=new JTextArea();
        textArea.setFont(new Font("Balthazar",Font.PLAIN,27));
        textArea.setText(readTextFromFile());
        textArea.setBackground(new Color(255,255,152));
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1180,500));
        add(scrollPane,gbc);

    }
    public String readTextFromFile() throws IOException {
        String text="";
        String tmp;
        int i=0;
        BufferedReader br = new BufferedReader(new FileReader("texts\\help.txt"));
        while((tmp=br.readLine())!=null){
            text+=tmp+"\n";
        }
        return text;
    }
    private void showMenuPanel(){
        MenuPanel menuPanel=new MenuPanel(frame);
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
