
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * Classe que organiza as informações sobre a imagem selecionada
 * Altura, Largura, variancia, Desvio Padrão, Rugosidade, flag grayscale, dizer que está em RGB
 */

/**
 * 01/06/11
 * @Georgea
 */
public class JanelaInfo extends JDialog
{

    private static class JOptionPane {

        
    }
    private JanelaInterna janelinha;  //pra conhecer os atributos e métodos dessa classe
   

    public JanelaInfo(JanelaInterna janelinha)
    {
        setLayout(null);
        this.janelinha = janelinha;
        setTitle(janelinha.getTitle());
        setSize(300, 250);
        
        JLabel texto1 = new JLabel("Informações da Imagem");
        texto1.setBounds(25, 10, 170 ,20);
        add(texto1);
        
        JLabel texto2 = new JLabel("Altura: "+janelinha.getAlturaImagem());
        texto2.setBounds(25, 40, 170 ,20);
        add(texto2);
        
        JLabel texto3 = new JLabel("Largura: "+janelinha.getLarguraImagem());
        texto3.setBounds(25, 60, 170 ,20);
        add(texto3);
        
        JLabel texto4 = new JLabel("Variância: "+variancia());
        texto4.setBounds(25, 80, 170 ,20);
        add(texto4);
        
        JLabel texto5 = new JLabel("Desvio Padrão: "+desvioPadrao());
        texto5.setBounds(25, 100, 170 ,20);
        add(texto5);
        
        JLabel texto6 = new JLabel("Rugosidade: "+rugosidade());
        texto6.setBounds(25, 120, 170 ,20);
        add(texto6);
        
        JLabel texto7 = new JLabel("Padrão de cores:    RGB ");
        texto7.setBounds(25, 140, 170 ,20);
        add(texto7);
        
        JLabel texto8 = new JLabel("Grayscale: ");
        texto8.setBounds(25, 160, 170 ,20);
        add(texto8);
        
        
        
            JCheckBox caixinha = new JCheckBox();
            caixinha.setBounds(90, 160, 20, 20);
            caixinha.doClick();
            caixinha.setEnabled(false);
            add(caixinha);
        
       
      // JTextField campo1 = new JTextField();
      // campo1.setBounds(25, 200, 50 ,20 );
      // add(campo1);
        setVisible(true);    
    }
   
    
    private double variancia()
    {
       
       //número de pixels
       double n = janelinha.getAlturaImagem()*janelinha.getLarguraImagem();
        
        //valor médio
        //double vm = ()/n;
        
        
      //  double var = Math.pow( nc - vm) , 2) /(n-1);
       double var =0;
        return var;
    }
    
    private double desvioPadrao()
    {
        double var = variancia();
        double desvio = Math.pow(var, 0.5); // é a raiz da variancia
        return desvio;
    }
    
    private double rugosidade()
    {
        double var = variancia();
        double rug = 1 - (1/(1+var));
        return rug;
    }

}
