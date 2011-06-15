
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
        } else if (e.getActionCommand().equals("gray")) {
            Controle.escalaDeCinza(janela.getSelectedFrame());
        } else if (e.getActionCommand().equals("oper")) {
            new JChooseFrame(janela);
        } else if (e.getActionCommand().equals("limiar")) {
            new JanelaLimiar(janela);
        } else if (e.getActionCommand().equals("toolbar")) {
            if (janela.getToolBarStatus() == janela.TOOL_BAR_ON) {
                ((JMenuItem) e.getSource()).setText("Toolbar OFF");
                janela.getToolBar().setVisible(false);
                janela.setToolBarStatus(janela.TOOL_BAR_OFF);


            } else {
                ((JMenuItem) e.getSource()).setText("Toolbar ON");
                janela.getToolBar().setVisible(true);
                janela.setToolBarStatus(janela.TOOL_BAR_ON);
                for (int i = 0; i < janela.getDesktop().getAllFrames().length; i++) {
                    if (janela.getDesktop().getAllFrames()[i].getY() <= 18) {
                        janela.getDesktop().getAllFrames()[i].setBounds(janela.getDesktop().getAllFrames()[i].getX(), 18, janela.getDesktop().getAllFrames()[i].getWidth(), janela.getDesktop().getAllFrames()[i].getHeight());
                    }
                }
            }
        } else if (e.getActionCommand().equals("histograma"))
        {
            janela.abrirJanelaHistograma();
        } else if(e.getActionCommand().equals("mostrar")) //relacionada ao JMenu Informações
        {
            JanelaInfo f = new JanelaInfo(janela.getSelectedFrame());
        } else if(e.getActionCommand().equals("fft")) {
            Controle.fftDireta(janela.getSelectedFrame());
        }

    }
}
