
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JChooseFrame extends JDialog // em operacoe com duas imagens, nesta voce poderá escolher quais utilizar
{
    private Janela janela;
    private JTable t1, t2;
    private JButton soma, sub, not, or, and, nor, nand, xor;
    JInternalFrame [] janelas;

    public JChooseFrame(Janela janela)
    {
        setModal(true);
        setLayout(null);
        setTitle("Opecões Lógicas e Aritméticas");
        setSize(500, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(janela);
        setResizable(false);

        janelas = janela.getDesktop().getAllFrames();
        
        int tamanho = janelas.length;
        
        t1 = new JTable(tamanho,1);
        t2 = new JTable(tamanho,1);

        t1.setSelectionMode(0);
        t2.setSelectionMode(0);

        t1.setTableHeader(null);
        t2.setTableHeader(null);

        DefaultTableModel model = new DefaultTableModel(tamanho, 1){
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };

        t1.setModel(model);
        t2.setModel(model);

        soma = new JButton("A + B");
        sub = new JButton("A - B");
        not = new JButton("NOT A");
        and = new JButton("A and B");
        or = new JButton("A or B");
        nand = new JButton("A nand B");
        nor = new JButton("A nor B");
        xor = new JButton("A xor B");

        for(int i=0;i<tamanho;i++)
        {
            t1.setValueAt(janelas[i].getTitle(), i, 0);
            t2.setValueAt(janelas[i].getTitle(), i, 0);
        }

        JScrollPane p1 = new JScrollPane(t1);
        JScrollPane p2 = new JScrollPane(t2);

        p1.setBounds(20, 20, 220, 150);
        p2.setBounds(260, 20, 220, 150);
        
        int x=-3;//para configuracao da posicao dos botoes
        soma.setBounds(20+x, 200, 85, 20);
        sub.setBounds(115+x, 200, 85, 20);
        not.setBounds(210+x, 200, 85, 20);
        and.setBounds(305+x, 200, 85, 20);
        or.setBounds(400+x, 200, 85, 20);
        int y=90;
        nand.setBounds(20+y, 240, 85, 20);
        nor.setBounds(115+y, 240, 85, 20);
        xor.setBounds(210+y, 240, 85, 20);
        
        soma.setActionCommand("soma");
        sub.setActionCommand("sub");
        not.setActionCommand("not");
        and.setActionCommand("and");
        or.setActionCommand("or");
        nand.setActionCommand("nand");
        nor.setActionCommand("nor");
        xor.setActionCommand("xor");

        JChooseFrameListener listener = new JChooseFrameListener(this);
        
        soma.addActionListener(listener);
        sub.addActionListener(listener);
        not.addActionListener(listener);
        and.addActionListener(listener);
        or.addActionListener(listener);
        nand.addActionListener(listener);
        nor.addActionListener(listener);
        xor.addActionListener(listener);
        
        add(soma);
        add(sub);
        add(not);
        add(and);
        add(or);
        add(nand);
        add(nor);
        add(xor);
        add(p1);
        add(p2);
        
        setVisible(true);        
    }

    public JanelaInterna getFrame1()
    {
        for(int i=0;i<janelas.length;i++)
        {
            if(janelas[i].getTitle().equals(t1.getValueAt(t1.getSelectedRow(), 0)))
                return (JanelaInterna)janelas[i];
        }
        return null;
    }

    public JanelaInterna getFrame2()
    {
        for(int i=0;i<janelas.length;i++)
        {
            if(janelas[i].getTitle().equals(t2.getValueAt(t2.getSelectedRow(), 0)))
                return (JanelaInterna)janelas[i];
        }
        return null;
    }

    public Janela getJanela()
    {
        return janela;
    }

}
