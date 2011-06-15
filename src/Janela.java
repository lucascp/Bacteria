import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Janela extends JFrame
{
    public static boolean TOOL_BAR_ON = true;
    public static boolean TOOL_BAR_OFF = false;
    public static Dimension screenSize;

    private JDesktopPane desktop;
    private JScrollPane scrollpane;
    private JanelaListener listener;
    private Controle controle;
    private JToolBar toolbar;

    public static Dimension getScreenSize() {
        return screenSize;
    }
    private boolean toolBarStatus = true; // a principio o toolBar aparece
    

    public JDesktopPane getDesktop() {
        return desktop;
    }
    
    public Janela()
    {
        super("Bactéria 2 -- versão Beta");
        
        controle = new Controle(this);
        
        listener = new JanelaListener(this);
        
        int inset = 50; //  usado no setBounds
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        getContentPane().setLayout(new BorderLayout());

        desktop=new JDesktopPane();
        scrollpane = new JScrollPane();
        scrollpane.setViewportView(desktop);
        this.add(scrollpane, BorderLayout.CENTER);
        this.setJMenuBar(criarBarraDeMenu());
        desktop.setBackground(Color.gray); //cor de fundo

        criarToolBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void abrirJanelaInterna()
    {
        JanelaInterna janelinha = new JanelaInterna(this);
        if(!janelinha.abrirImagem()) {
            return;
        }
        desktop.add(janelinha);
        try {
            janelinha.setSelected(true);
        } catch (PropertyVetoException ex) {}
    }

    public void abrirJanelaHistograma()
    {
        JanelaInterna janelinha0 = getSelectedFrame();
        if (janelinha0 == null) return;
        JanelaHistograma janelinha = new JanelaHistograma(this, janelinha0.getRed(), janelinha0.getGreen(), 
                janelinha0.getBlue(), janelinha0.getRed(), janelinha0.getAlturaImagem(), 
                janelinha0.getLarguraImagem(), janelinha0.isGrayScale(), janelinha0.getTitle());
    }
    
    public void adicionarJanelaInterna(JanelaInterna j)
    {
        desktop.add(j);
        try {
            j.setSelected(true);
        } catch (PropertyVetoException ex) {}

    }

    public JanelaInterna getSelectedFrame()
    {
        return (JanelaInterna)desktop.getSelectedFrame();
    }


    public JToolBar criarToolBar()
    {
        toolbar = new JToolBar();
        
        JButton b = new JButton(UIManager.getIcon("FileView.directoryIcon"));
        b.setActionCommand("abrir");
        b.addActionListener(listener);
        toolbar.add(b);
        
        b = new JButton(UIManager.getIcon("FileView.floppyDriveIcon"));
        //b.setActionCommand("salvar"); // TODO
        b.addActionListener(listener);
        toolbar.add(b);
        
        this.add(toolbar, BorderLayout.NORTH);

        return toolbar;
    }

    public JMenuBar criarBarraDeMenu()
    {
        JMenuBar barra = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setMnemonic('a');
        JMenu operacoes = new JMenu("Operações");
        operacoes.setMnemonic('o');
        JMenu visualizar = new JMenu("Visualizar");
        visualizar.setMnemonic('v');

        //adicionadas no JMenu arquivo
        JMenuItem abrir = new JMenuItem("Abrir");
        abrir.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        abrir.setActionCommand("abrir");

        JMenuItem salvar = new JMenuItem("Salvar");
        salvar.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        salvar.setActionCommand("salvar");
        
        JMenuItem sair = new JMenuItem("Sair");
        sair.setActionCommand("sair");

        JMenuItem grayscale = new JMenuItem("Grayscale");
        grayscale.setActionCommand("gray");

        //adicionadas no JMenu operacoes
        JMenuItem soma = new JMenuItem("Lógicas e Aritméticas");
        soma.setActionCommand("oper");

        JMenuItem limiar = new JMenuItem("Limiarização");
        limiar.setActionCommand("limiar");
        
        //adicionadas no JMenu histograma/equalização
        JMenuItem histograma = new JMenuItem("Histograma/Equalização");
        histograma.setActionCommand("histograma");
        
        arquivo.add(abrir);
        arquivo.add(salvar);
        arquivo.add(new JSeparator());
        arquivo.add(sair);

        operacoes.add(soma);
        operacoes.add(grayscale);
        operacoes.add(limiar);
        operacoes.add(histograma);
        
        //adicionadas no JMenu visualizar
        JCheckBoxMenuItem jtoolbar = new JCheckBoxMenuItem("Barra de Ferramentas");
        jtoolbar.setSelected(true);
        jtoolbar.setActionCommand("toolbar");
        
        visualizar.add(jtoolbar);

        //adiciona tudo na jtoolbar
        barra.add(arquivo);
        barra.add(operacoes);
        barra.add(visualizar);
        barra.add(new MenuJanela(desktop));

        abrir.addActionListener(listener);
        salvar.addActionListener(listener);
        sair.addActionListener(listener);
        grayscale.addActionListener(listener);
        jtoolbar.addActionListener(listener);
        soma.addActionListener(listener);
        limiar.addActionListener(listener);
        histograma.addActionListener(listener);

        JMenu info = new JMenu("Informações"); //Adicionado por G.
        info.setMnemonic('i');

        JMenuItem mostrar = new JMenuItem("Mostrar...");//info
        mostrar.setActionCommand("mostrar");
        info.add(mostrar); //info
        barra.add(info); //info
        mostrar.addActionListener(listener);//info

        
        return barra;
    }

    boolean getToolBarStatus() {
        return toolBarStatus;
    }

    public JToolBar getToolBar()
    {
        return toolbar;
    }
    

    public void setToolBarStatus(boolean toolBarStatus) {
        this.toolBarStatus = toolBarStatus;
    }

}

class MenuJanela extends JMenu {

    private JDesktopPane desktopPane;
    private JMenuItem cascata = new JMenuItem("Cascata");
    private JMenuItem ladoALado = new JMenuItem("Lado a Lado");
    private JMenuItem fecharJanela = new JMenuItem("Fechar");
    private static int FRAME_OFFSET = 35;

    public MenuJanela(JDesktopPane desktop) {
        this.desktopPane = desktop;

        setText("Janela");
        setMnemonic('j');
        fecharJanela.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    desktopPane.getSelectedFrame().setClosed(true);
                } catch (Exception ex) {}
            }
        });
        fecharJanela.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        addMenuListener(new MenuListener() {

            public void menuSelected(MenuEvent me) {
                buildChildMenus();
            }

            public void menuDeselected(MenuEvent me) {
            }

            public void menuCanceled(MenuEvent me) {
            }
        });
        
        buildChildMenus();
    }

    private void buildChildMenus() {
        JInternalFrame janelas[] = desktopPane.getAllFrames();
        
        removeAll();
        
        add(cascata);

        cascata.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                JInternalFrame janelas[] = desktopPane.getAllFrames();

                int x = 0;
                int y = 0;

                for (int i = janelas.length - 1; i >= 0; i--) {
                    JInternalFrame janela = janelas[i];
                    janela.setLocation(x, y);
                    x = x + FRAME_OFFSET;
                    y = y + FRAME_OFFSET;
                }
            }
        });

        ladoALado.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                JInternalFrame janelas[] = desktopPane.getAllFrames();

                int frameHeight = desktopPane.getBounds().height / janelas.length;
                int y = 0;
                for (JInternalFrame janela : janelas) {
                    janela.setSize(desktopPane.getBounds().width, frameHeight);
                    janela.setLocation(0, y);
                    y += frameHeight;
                }
            }
        });


        add(ladoALado);

        if (janelas.length > 0) {
            addSeparator();
        }

        cascata.setEnabled(janelas.length > 0);
        ladoALado.setEnabled(janelas.length > 0);

        for (JInternalFrame janela : janelas) {
            MenuJanelaItem menuItem = new MenuJanelaItem(janela);
            menuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    JInternalFrame frame = ((MenuJanelaItem) ae.getSource()).getFrame();
                    frame.toFront();
                    try {
                        frame.setSelected(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(MenuJanela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            add(menuItem);
        }
        
        addSeparator();
        add(fecharJanela);
    }

    class MenuJanelaItem extends JMenuItem {

        private JInternalFrame frame;

        public MenuJanelaItem(JInternalFrame frame) {
            super(frame.getTitle());
            this.frame = frame;
        }

        private JInternalFrame getFrame() {
            return this.frame;
        }
    }
}
