
import javax.swing.JDialog;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TCC
 */
public class JanelaHistograma extends JDialog {
    public static final int MAX_RANGE_NIVEIS = 255;
    private Janela janela;      
    /////////////////////////////////////////////////////////////
    // Atributos da imagem
    private float red[][];
    private float green[][];
    private float blue[][];
    private float gray[][];
    private int alturaImg;
    private int larguraImg;
    /////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////
    // Contadores de nivel por canal
    private int cRed[] = new int[256];
    private int cGreen[] = new int[256];
    private int cBlue[] = new int[256];
    private int cGray[] = new int[256];
    /////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////
    // Probabilidades de nivel por canal
    private float pRed[] = new float[256];
    private float pGreen[] = new float[256];
    private float pBlue[] = new float[256];
    private float pGray[] = new float[256];
    /////////////////////////////////////////////////////////////
    
    public JanelaHistograma(Janela janela, float red[][], float green[][], float blue[][], float gray[][], int alturaImg, int larguraImg)
    {
        this.janela = janela;
        this.alturaImg = alturaImg;
        this.larguraImg = larguraImg;
        this.red = red;
        this.green = green;
        this.blue = blue;
        
        /////////////////////////////////////////
        // Operações com histograma
        histogramaRGB();
        /////////////////////////////////////////
        setVisible(true);
    }
    
    private void histogramaRGB()
    {
        int num = alturaImg*larguraImg;
        for (int i = 0; i < larguraImg; i++)
            for (int j = 0; j < alturaImg; j++)
            {
                int r = (int) red[i][j];
                int g = (int) green[i][j];
                int b = (int) blue[i][j];
                cRed[r]++;
                cGreen[g]++;
                cBlue[b]++;
            }
        for (int i = 0; i < MAX_RANGE_NIVEIS; i++)
        {
            pRed[i] = (float) cRed[i] / num;
            pGreen[i] = (float) cGreen[i] / num;
            pBlue[i] = (float) cBlue[i] / num;
        }
    }
    
    private void histogramaGray()
    {
        int num = alturaImg*larguraImg;
        for (int i = 0; i < larguraImg; i++)
            for (int j = 0; j < alturaImg; j++)
            {
                int gy = (int) gray[i][j];
                cGray[gy]++;
            }
        for (int i = 0; i < MAX_RANGE_NIVEIS; i++)
            pGray[i] = (float) cGray[i] / num;
    }
    
}


