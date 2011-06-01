
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class JanelaLimiarizacao extends JDialog
{
    private JLabel label;
    private JTextField textArea;
    private JButton ok;
    Janela j;

    JanelaLimiarizacao janelaLimiarizacao = this;

    public JanelaLimiarizacao(Janela janela)
    {
        this.j=janela;
        setModal(true);
        setLayout(new GridLayout(1, 3));
        setTitle("Limiarizacao");
        setSize(200, 70);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(janela);
        setResizable(false);

        label = new JLabel("Limiar:");
        textArea = new JTextField();
        ok = new JButton("OK");

        add(label);
        add(textArea);
        add(ok);

        ok.addActionListener(new JanelaLimiarizacaoListener(j, this));
        
        textArea.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {if(e.getKeyCode()==e.VK_ENTER)ok.doClick();}
            public void keyReleased(KeyEvent e) {}
        });

        setVisible(true);
    }

    public String getText() {
        return textArea.getText();
    }

}
