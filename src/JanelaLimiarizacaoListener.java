
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
        Controle.limiarBinario(janela.getSelectedFrame(), Integer.parseInt(janelaLimiar.getText()));
        janelaLimiar.dispose();
        
    }

}
