
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 * Classe que organiza as informações sobre a imagem selecionada
 * Altura, Largura, Desvio Padrão, Rugosidade, flag grayscale
 */

/**
 * 01/06/11
 * @Georgea
 */
public class JanelaInfo extends JDialog
{
    private JanelaInterna janelinha;  //pra conhecer os atributos e métodos dessa classe


    public JanelaInfo(JanelaInterna janelinha)
    {
        this.janelinha = janelinha;
        setTitle(janelinha.getTitle());
        setSize(300, 500);
        setVisible(true);
    }




}
