
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class JanelaListener implements ActionListener {

    private Janela janela;

    public JanelaListener(Janela janela) {
        this.janela = janela;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("abrir")) {
            janela.abrirJanelaInterna();
        } else if (e.getActionCommand().equals("importarTexto")) {
            janela.importarTextoJanelaInterna();
        } else if (e.getActionCommand().equals("exportarTexto")) {
            janela.exportarTextoJanelaInterna();
        } else if (e.getActionCommand().equals("gray")) {
            Controle.escalaDeCinza(janela.getSelectedFrame());
        } else if (e.getActionCommand().equals("oper")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JChooseFrame(janela);
            }            
        } else if (e.getActionCommand().equals("limiar")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaLimiar(janela);
            }
        } else if (e.getActionCommand().equals("toolbar")) {
            if (janela.getToolBarStatus() == janela.TOOL_BAR_ON) {
                janela.getToolBar().setVisible(false);
                janela.setToolBarStatus(janela.TOOL_BAR_OFF);
            } else {
                janela.getToolBar().setVisible(true);
                janela.setToolBarStatus(janela.TOOL_BAR_ON);
            }
        } else if (e.getActionCommand().equals("histograma"))
        {
            janela.abrirJanelaHistograma();
        } else if(e.getActionCommand().equals("mostrar")) //relacionada ao JMenu Informações
        {
            JanelaInfo f = new JanelaInfo(janela.getSelectedFrame());
        } else if(e.getActionCommand().equals("dft")) {
            Controle.fftDireta(janela.getSelectedFrame());
        } else if(e.getActionCommand().equals("idft")) {
            Controle.fftInversa(janela.getSelectedFrame());
        } else if(e.getActionCommand().equals("sair")) {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("zoomin")) //relacionada ao JMenu Informações
        {
            Controle.zoomIn(janela.getSelectedFrame());
        }
        else if(e.getActionCommand().equals("zoomout")) //relacionada ao JMenu Informações
        {
            Controle.zoomOut(janela.getSelectedFrame());
        }
        else if(e.getActionCommand().equals("erosao")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Erosão");
            }
        }
        else if(e.getActionCommand().equals("dilatacao")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Dilatação");
            }
        }
        else if(e.getActionCommand().equals("abertura")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Abertura");
            }
        }
        else if(e.getActionCommand().equals("fechamento")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Fechamento");
            }
        }  
        else if(e.getActionCommand().equals("gradDilatacao")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Gradiente da Dilatação");
            }
        }
        else if(e.getActionCommand().equals("gradErosao")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "Gradiente da Erosão");
            }
        }  
        else if(e.getActionCommand().equals("topHatPicos")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "TOP-HAT Picos");
            }
        }  
        else if(e.getActionCommand().equals("topHatVales")) {
            if(!(janela.getSelectedFrame() == null)) {
                new JanelaMorfologia(janela, "TOP-HAT Vales");
            }
        }  

    }
}
