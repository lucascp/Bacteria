
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JChooseFrameListener implements ActionListener
{
    private JChooseFrame janela;

    public JChooseFrameListener(JChooseFrame janela)
    {
        this.janela=janela;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("soma"))
        {
            Controle.soma(janela.getFrame1(),janela.getFrame2());
        }
        else if(e.getActionCommand().equals("sub"))
        {
            Controle.subtrai(janela.getFrame1(),janela.getFrame2());
        }
        else if(e.getActionCommand().equals("not"))
        {
            Controle.not(janela.getFrame1());
        }
        else if(e.getActionCommand().equals("and"))
        {
            Controle.and(janela.getFrame1(),janela.getFrame2());            
        }
        else if(e.getActionCommand().equals("or"))
        {
            Controle.or(janela.getFrame1(),janela.getFrame2());            
        }
        else if(e.getActionCommand().equals("nand"))
        {
            Controle.nand(janela.getFrame1(),janela.getFrame2());            
        }
        else if(e.getActionCommand().equals("nor"))
        {
            Controle.nor(janela.getFrame1(),janela.getFrame2());            
        }
        else if(e.getActionCommand().equals("xor"))
        {
            Controle.xor(janela.getFrame1(),janela.getFrame2());
        }
        
        janela.dispose();
    }

}
