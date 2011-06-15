
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class JanelaInterna extends JInternalFrame {

    private BufferedImage bufImage;
    private Image imagem;
    private float red[][];
    private float green[][];
    private float blue[][];
    private int larguraImagem, alturaImagem;
    private Janela janela;
    boolean aux = false;

    public JanelaInterna(Janela janela) {
        this.janela = janela;
        setSize(300, 200);
        setMaximizable(true);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(true);
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    /**
     * Abre um JFileChooser para o usuário escolher a imagem e abre-a.
     * Retorna true se a imagem tiver sido carregada com sucesso, false se não.
     * @return true se a imagem tiver sido carregada, false se não
     */
    public boolean abrirImagem()
    {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            // A janela de abrir imagem foi cancelada ou houve algum erro misterioso
            return false;
        }
        this.setTitle(chooser.getSelectedFile().getName()); // faz a janela escrever o nome do arquivo

        try {
            bufImage = ImageIO.read(chooser.getSelectedFile());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir a imagem!");
        }
        criarMatrizes();

        imagem = Toolkit.getDefaultToolkit().createImage(bufImage.getSource());
        ImageIcon ic = new ImageIcon(imagem);
        alturaImagem = bufImage.getHeight();
        larguraImagem = bufImage.getWidth();

        JLabel l = new JLabel(ic);
        add(l);
        JScrollPane pane = new JScrollPane(l);
        add(pane);
        setSize(larguraImagem+20, alturaImagem+40);

        // tudo ok :)
        return true;
    }

    public float[][] getBlue() // Em java as matrizes são tratadas como ponteiros, então com esse método retorna uma cópia
    {
        float[][] maux = new float[larguraImagem][alturaImagem];

        for (int i = 0; i < larguraImagem; i++) {
            System.arraycopy(blue[i], 0, maux[i], 0, alturaImagem);
        }

        return maux;
    }

    public float[][] getGreen() {
        float[][] maux = new float[larguraImagem][alturaImagem];

        for (int i = 0; i < larguraImagem; i++) {
            System.arraycopy(green[i], 0, maux[i], 0, alturaImagem);
        }

        return maux;
    }

    public float[][] getRed() {
        float[][] maux = new float[larguraImagem][alturaImagem];

        for (int i = 0; i < larguraImagem; i++) {
            System.arraycopy(red[i], 0, maux[i], 0, alturaImagem);
        }

        return maux;
    }

    public void setMatrizRed(float[][] red) {
        this.red = red;
    }

    public void setMatrizGreen(float[][] green) {
        this.green = green;
    }

    public void setMatrizBlue(float[][] blue) {
        this.blue = blue;
    }

    public void criarImagem() {
        bufImage = new BufferedImage(larguraImagem, alturaImagem, BufferedImage.TYPE_INT_RGB);
        Color c;

        for (int i = 0; i < larguraImagem; i++) {
            for (int j = 0; j < alturaImagem; j++) {
                c = new Color((int) red[i][j], (int) green[i][j], (int) blue[i][j]);
                bufImage.setRGB(i, j, c.getRGB());
            }
        }


        imagem = Toolkit.getDefaultToolkit().createImage(bufImage.getSource());
        ImageIcon ic = new ImageIcon(imagem);
        JLabel l = new JLabel(ic);
        add(l);
        JScrollPane pane = new JScrollPane(l);
        add(pane);
        setSize(larguraImagem + 20, alturaImagem + 40);
    }

    private void criarMatrizes() // transforma a BufferedImage numa matriz
    {
        Color cor;

        red = new float[bufImage.getWidth()][bufImage.getHeight()];
        green = new float[bufImage.getWidth()][bufImage.getHeight()];
        blue = new float[bufImage.getWidth()][bufImage.getHeight()];

        for (int i = 0; i < bufImage.getWidth(); i++) {
            for (int j = 0; j < bufImage.getHeight(); j++) {
                cor = new Color(bufImage.getRGB(i, j));
                red[i][j] = cor.getRed();
                green[i][j] = cor.getGreen();
                blue[i][j] = cor.getBlue();
            }
        }
    }
}
