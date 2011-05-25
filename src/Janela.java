
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Janela extends JFrame
{
    public static boolean TOOL_BAR_ON = true;
    public static boolean TOOL_BAR_OFF = false;
    public static Dimension screenSize;

    private JDesktopPane desktop;
    private JanelaListener listener;
    private Controle controle;
    private JMenuBar toolbar;

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

        desktop=new JDesktopPane();
        this.setContentPane(desktop);
        this.setJMenuBar(criarBarraDeMenu());
        desktop.setBackground(Color.gray); //cor de fundo

        criarToolBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void abrirJanelaInterna()
    {
        JanelaInterna janelinha = new JanelaInterna(this);
        janelinha.abrirImagem();
        desktop.add(janelinha);
        try {
            janelinha.setSelected(true);
        } catch (PropertyVetoException ex) {}
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


    public JMenuBar criarToolBar()
    {
       JMenuBar toolbar = new JMenuBar();
       toolbar.setLayout(null);
        JButton b = new JButton("Start");
        b.setBounds(5, 0, 19, 19);
        b.setBackground(Color.lightGray);
        toolbar.add(b);
        toolbar.setBounds(0, 0, screenSize.width, 20);
        add(toolbar);
        this.toolbar=toolbar;

        return toolbar;
    }

    public JMenuBar criarBarraDeMenu()
    {
        JMenuBar barra = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        JMenu operacoes = new JMenu("Operações");

        //adicionadas no JMenu arquivo
        JMenuItem abrir = new JMenuItem("Abrir...");
        abrir.setActionCommand("abrir");

        JMenuItem salvar = new JMenuItem("Salvar...");

        JMenuItem grayscale = new JMenuItem("Grayscale");
        grayscale.setActionCommand("gray");

        JMenuItem toolbar = new JMenuItem("Toolbar ON");
        toolbar.setActionCommand("toolbar");

        //adicionadas no JMenu operacoes
        JMenuItem soma = new JMenuItem("Lógicas e Aritméticas");
        soma.setActionCommand("oper");
        
        arquivo.add(abrir);
        arquivo.add(salvar);        
        arquivo.add(toolbar);

        operacoes.add(soma);
        operacoes.add(grayscale);

        barra.add(arquivo);
        barra.add(operacoes);

        abrir.addActionListener(listener);
        grayscale.addActionListener(listener);
        toolbar.addActionListener(listener);
        soma.addActionListener(listener);

        return barra;
    }

    boolean getToolBarStatus() {
        return toolBarStatus;
    }

    public JMenuBar getToolBar()
    {
        return toolbar;
    }
    

    public void setToolBarStatus(boolean toolBarStatus) {
        this.toolBarStatus = toolBarStatus;
    }

}
