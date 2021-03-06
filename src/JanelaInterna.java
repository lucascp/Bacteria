
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private float real[][];
    private float imag[][];
    private int larguraImagem, alturaImagem;
    private boolean isBinary;
    private boolean isGrayScale;
    private boolean isDFT;
    private boolean isFlagInicializadas;
    
    
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
        isFlagInicializadas = false;
        real = imag = null;
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
    
    public boolean importarTexto()
    {
        LineNumberReader lnr;
        try {
            JFileChooser chooser = new JFileChooser();
            if(chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                // A janela de abrir imagem foi cancelada ou houve algum erro misterioso
                return false;
            }
            
            // determina a largura e a altura da matriz
            int largura = Integer.MAX_VALUE;
            int altura = 0;
            
            lnr = new LineNumberReader(new FileReader(chooser.getSelectedFile().getAbsoluteFile()));
            lnr.setLineNumber(1);
            StreamTokenizer stok = new StreamTokenizer(lnr);
            stok.parseNumbers();
            stok.eolIsSignificant(true);
            stok.nextToken();
            
            while(stok.ttype != StreamTokenizer.TT_EOF) {
                altura++;
                int cnt = 0;
                while (stok.ttype != StreamTokenizer.TT_EOL) {
                    if (stok.ttype == StreamTokenizer.TT_NUMBER)
                        cnt++;
                    stok.nextToken();
                }
                largura = Math.min(cnt, largura);
                stok.nextToken();
            }
            
            lnr.close();
            
            
            // carrega a matriz do arquivo
            System.out.println("Carregando arquivo com " + altura + " linhas e " + largura + " colunas.");
            float matriz[][] = new float[largura][altura];
            
            lnr = new LineNumberReader(new FileReader(chooser.getSelectedFile().getAbsoluteFile()));
            lnr.setLineNumber(1);
            stok = new StreamTokenizer(lnr);
            stok.parseNumbers();
            stok.eolIsSignificant(true);
            stok.nextToken();
            
            for(int i = 0; stok.ttype != StreamTokenizer.TT_EOF; i++) {
                int cnt = 0;
                for (int j = 0; stok.ttype != StreamTokenizer.TT_EOL; j++) {
                    if (stok.ttype == StreamTokenizer.TT_NUMBER)
                        matriz[j][i] = (float) stok.nval;
                    stok.nextToken();
                }
                stok.nextToken();
            }
            
            lnr.close();
            
            // carrega a imagem
            setTitle(chooser.getSelectedFile().getName()); // faz a janela escrever o nome do arquivo
            setMatrizBlue(matriz);
            setMatrizRed(matriz);
            setMatrizGreen(matriz);
            setLarguraImagem(largura);
            setAlturaImagem(altura);
            criarImagem();
            
            lnr.close();
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(JanelaInterna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void exportarTexto() {
        JFileChooser chooser = new JFileChooser();
        
        if(!isGrayScale()) {
            JOptionPane.showMessageDialog(null, "A imagem deve estar em escala de cinza.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            // A janela de exportar foi cancelada ou houve algum erro misterioso
            return;
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(chooser.getSelectedFile().getAbsoluteFile()));
            
            for(int i = 0; i < alturaImagem; i++) {
                for(int j = 0; j < larguraImagem; j++) {
                    out.write(((int) blue[i][j]) + " ");
                }
                out.write('\n');
            }
            
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(JanelaInterna.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public float[][] getReal() {
        float[][] maux = new float[larguraImagem][alturaImagem];

        for (int i = 0; i < larguraImagem; i++)
            System.arraycopy(real[i], 0, maux[i], 0, alturaImagem);
        
        return maux;
    }
    
    public float[][] getImag() {
        float[][] maux = new float[larguraImagem][alturaImagem];

        for (int i = 0; i < larguraImagem; i++)
            System.arraycopy(imag[i], 0, maux[i], 0, alturaImagem);
        
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
    
    public void setMatrizReal(float[][] real) {
        this.real = real;
    }
    
    public void setMatrizImag(float[][] imag) {
        this.imag = imag;
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
    
    public void inicializaFlags()
    {
        float eps = 1e-4f;
        isBinary = true;
        isGrayScale = true;
        isDFT = true;
        for (int i = 0; i < larguraImagem; i++)
            for (int j = 0; j < alturaImagem; j++)
            {
                if (!(Math.abs(red[i][j] - green[i][j]) < eps && Math.abs(red[i][j] - blue[i][j]) < eps))
                {
                    isBinary = false;
                    isGrayScale = false;
                    isFlagInicializadas = true;
                    return;
                }
                if (Math.abs(red[i][j] - 0.0f) > eps && Math.abs(red[i][j] - 255.0f) > eps)
                    isBinary = false;
            }
        if(real == null || imag == null)
            isDFT = false;
        isFlagInicializadas = true;
    }

    public boolean isGrayScale()
    {
        if (!isFlagInicializadas)
            inicializaFlags();
        return isGrayScale;
    }
    public boolean isBinary()
    {
        if (!isFlagInicializadas)
            inicializaFlags();
        return isBinary;
    }
    public boolean isDFT()
    {
        if (!isFlagInicializadas)
            inicializaFlags();
        return isDFT;
    }
    public void setDFT(boolean flag) {
        this.isDFT = flag;
    }
    
    public void mostraMagDFT() {
        if(isDFT == false)
            return;
        
        float [][]mag = new float[larguraImagem][alturaImagem];
        int i, j;
        
        for(i = 0; i < larguraImagem; i++)
            for(j = 0; j < alturaImagem; j++)
                mag[i][j] = (float)Math.sqrt(real[i][j]*real[i][j] + imag[i][j]*imag[i][j]);
        
        mag = Controle.transformacaoLog(mag, larguraImagem, alturaImagem, 1.0);
        mag = Controle.arrumaEscala(mag, larguraImagem, alturaImagem);
        
        this.red = mag;
        this.green = mag;
        this.blue = mag;
        this.setTitle("DFT de " + this.getTitle());
        
        this.criarImagem();
    }
}
