
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaLimiarizacaoListener implements ActionListener
{
    Janela janela;
    JanelaLimiarizacao janelaLimiarizacao;
    
    public JanelaLimiarizacaoListener(Janela j, JanelaLimiarizacao j2)
    {
        janela=j;
        janelaLimiarizacao=j2;
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println(janela.getSelectedFrame().getTitle());
        Controle.limiarBinario(janela.getSelectedFrame(), Integer.parseInt(janelaLimiarizacao.getText()));
        janelaLimiarizacao.dispose();
        
    }

}
