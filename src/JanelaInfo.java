
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * Classe que organiza as informações sobre a imagem selecionada
 * Nome: ok
 * Altura: ok
 * Largura: ok
 * numero de pixels: ok
 * media ok
 * variancia: ok 
 * Desvio Padrão: ok
 * Rugosidade: ok
 * flag grayscale: ok
 * flag binario: ok
 * dizer que está em RGB: ok
 * acertar para não-grayscale: ok
 * problema da demora: ok
 */

/**
 * 22/06/11
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
        setSize(300, 320);
        
        JLabel texto1 = new JLabel("Informações da Imagem");
        texto1.setBounds(25, 10, 170 ,20);
        add(texto1);
        
        JLabel texto2 = new JLabel("Nome: "+janelinha.getTitle());
        texto2.setBounds(25, 40, 250 ,20);
        add(texto2);
        
        JLabel texto3 = new JLabel("Altura: "+janelinha.getAlturaImagem());
        texto3.setBounds(25, 60, 170 ,20);
        add(texto3);
        
        JLabel texto4 = new JLabel("Largura: "+janelinha.getLarguraImagem());
        texto4.setBounds(25, 80, 250 ,20);
        add(texto4);
        
        JLabel texto5 = new JLabel("Número de pixels: "+numeroDePixels());
        texto5.setBounds(25, 100, 250 ,20);
        add(texto5);
        
        if(janelinha.isGrayScale())
        {
            JLabel texto6 = new JLabel("Média: "+valorMedio());
            texto6.setBounds(25, 120, 250 ,20);
            add(texto6);
        }else
        {
            JLabel texto6 = new JLabel("Média: não está em Grayscale");
            texto6.setBounds(25, 120, 250 ,20);
            add(texto6);
        }
        
        if(janelinha.isGrayScale())
        {
            JLabel texto7 = new JLabel("Variância: "+variancia());
            texto7.setBounds(25, 140, 250 ,20);
            add(texto7);
        }else
        {
            JLabel texto7 = new JLabel("Variância: não está em Grayscale");
            texto7.setBounds(25, 140, 250 ,20);
            add(texto7);
        }
        
        if(janelinha.isGrayScale())
        {
            JLabel texto8 = new JLabel("Desvio Padrão: "+desvioPadrao());
            texto8.setBounds(25, 160, 250 ,20);
            add(texto8);
        }else
        {
            JLabel texto8 = new JLabel("Desvio Padrão: não está em Grayscale");
            texto8.setBounds(25, 160, 250 ,20);
            add(texto8);
        }
        
        if(janelinha.isGrayScale())
        {
            JLabel texto9 = new JLabel("Rugosidade: "+rugosidade());
            texto9.setBounds(25, 180, 250 ,20);
            add(texto9);
        }else
        {
            JLabel texto9 = new JLabel("Rugosidade: não está em Grayscale");
            texto9.setBounds(25, 180, 250 ,20);
            add(texto9);
        }
                
        JLabel texto10 = new JLabel("Padrão de cores:    RGB ");
        texto10.setBounds(25, 200, 170 ,20);
        add(texto10);
        
        JLabel texto11 = new JLabel("Grayscale: ");
        texto11.setBounds(25, 220, 170 ,20);
        add(texto11);
        
        
        if(janelinha.isGrayScale())
        {
            JCheckBox caixinha = new JCheckBox();
            caixinha.setBounds(90, 220, 20, 20);
            caixinha.doClick();
            caixinha.setEnabled(false);
            add(caixinha);
        }
        else
        {
            JCheckBox caixinha = new JCheckBox();
            caixinha.setBounds(90, 220, 20, 20);
            caixinha.setEnabled(false);
            add(caixinha);            
        }
        
        JLabel texto12 = new JLabel("Binário: ");
        texto12.setBounds(25, 240, 170 ,20);
        add(texto12);
        
        if(janelinha.isBinary())
        {
            JCheckBox caixinha2 = new JCheckBox();
            caixinha2.setBounds(90, 240, 20, 20);
            caixinha2.doClick();
            caixinha2.setEnabled(false);
            add(caixinha2);
        }
        else
        {
            JCheckBox caixinha2 = new JCheckBox();
            caixinha2.setBounds(90, 240, 20, 20);
            caixinha2.setEnabled(false);
            add(caixinha2);            
        }
        
       
      // JTextField campo1 = new JTextField();
      // campo1.setBounds(25, 200, 50 ,20 );
      // add(campo1);
        setLocationRelativeTo(null);
        setVisible(true);    
    }
   
    private int numeroDePixels()
    {
       //número de pixels
       int n = janelinha.getAlturaImagem()*janelinha.getLarguraImagem();
       return n;    
    }
    
    private double valorMedio()
    {
        /* somatória dos valores de níveis de cinza nc 
         * de todos os n pixels 
         * dividido pelo número total de pixels n*/
        //o valor de nível de cinza pode ser red, blue ou green - pois está em grayscale
        double vm = 0;
        
        int linhas = janelinha.getLarguraImagem();
        int colunas = janelinha.getAlturaImagem();
        float matriz[][] = janelinha.getBlue();
        
        for(int i = 0; i<linhas; i++)
        {
            for(int j = 0; j<colunas; j++)
            {
                vm = vm + matriz[i][j];
            }
        }
        
        int n = numeroDePixels();
        vm = vm/n;
        return vm;
    }
    
    private double variancia()
    {
       
        double var = 0;
        
        int linhas = janelinha.getLarguraImagem();
        int colunas = janelinha.getAlturaImagem();
        double somatorio = 0;
        double vm = valorMedio();
        float matriz[][] = janelinha.getBlue();
        
        for(int i = 0; i<linhas; i++)
        {
            for(int j = 0; j<colunas; j++)
            {
                somatorio = somatorio + Math.pow((matriz[i][j] - vm), 2);
            }
        }
       
        var = somatorio;
        int n = numeroDePixels();
        var = var/(n-1);
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
