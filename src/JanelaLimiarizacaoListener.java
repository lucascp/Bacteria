
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaLimiarizacaoListener implements ActionListener
{
    Janela janela;
    JanelaLimiar janelaLimiar;
    
    public JanelaLimiarizacaoListener(Janela j, JanelaLimiar j2)
    {
        janela=j;
        janelaLimiar=j2;
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println(janela.getSelectedFrame().getTitle());
        if(janelaLimiar.getComboBox().getSelectedIndex()==0)
        {
            Controle.limiarBinario(janela.getSelectedFrame(), Integer.parseInt(janelaLimiar.getText()));
        }
        else if(janelaLimiar.getComboBox().getSelectedIndex() == 1) // Se estiver toWhite na combo-box
        {
            Controle.limiarToWhite(janela.getSelectedFrame(), Integer.parseInt(janelaLimiar.getText()));
        }
        else if(janelaLimiar.getComboBox().getSelectedIndex() == 2)
        {
            Controle.limiarToBlack(janela.getSelectedFrame(), Integer.parseInt(janelaLimiar.getText()));
        }
        
        janelaLimiar.dispose();
        
    }

}
