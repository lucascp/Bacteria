
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

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
    private boolean grayScale;
    private String title;
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
    private double pRed[] = new double[256];
    private double pGreen[] = new double[256];
    private double pBlue[] = new double[256];
    private double pGray[] = new double[256];
    /////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////
    // Componentes visuais
    private JPanel jpnlButtons;
    private JToggleButton jbtnRed;
    private JToggleButton jbtnGreen;
    private JToggleButton jbtnBlue;
    private JToggleButton jbtnGray;
    private JButton jbtnEqualizar;
    private JPanel jpnlBottom;
    private JPanel jpnlChart;
    private ChartPanel chartPanel;
    private JFreeChart jfreeChart;
    private XYPlot xyplot;
    private DefaultXYDataset xyDataSet;  
    private XYItemRenderer renderer;
    
    public JanelaHistograma(Janela janela, float red[][], float green[][],
            float blue[][], float gray[][], int alturaImg, int larguraImg, boolean grayScale, String title)
    {
        this.janela = janela;
        this.alturaImg = alturaImg;
        this.larguraImg = larguraImg;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.gray = gray;
        this.grayScale = grayScale;
        this.title = title;
        //////////////////////////////////////////
        // Constrói interface
        construirInterface();
        //////////////////////////////////////////
        
        /////////////////////////////////////////
        // Operações com histograma
        if (grayScale)
            histogramaGray();
        else
            histogramaRGB();
        /////////////////////////////////////////
        
        /////////////////////////////////////////
        // Plotagem dos gráficos
        gerarXYDataSet();
        /////////////////////////////////////////
        setTitle("Histograma de " + title);
        setVisible(true);
    }
    
    private void construirInterface()
    {
        Container parent = getContentPane();
        parent.setLayout(new BorderLayout());
        /////////////////////////////////////////
        // JPanel buttons = R,G,B,G
        // Seleção do tipo de gráfico
        jpnlButtons = new JPanel(new GridLayout(1, 4));
        jbtnRed = new JToggleButton("Red", !grayScale);
        jbtnGreen = new JToggleButton("Green", !grayScale);
        jbtnBlue = new JToggleButton("Blue", !grayScale);
        jbtnGray = new JToggleButton("Gray", grayScale);
        jpnlButtons.add(jbtnRed);
        jpnlButtons.add(jbtnGreen);
        jpnlButtons.add(jbtnBlue);
        jpnlButtons.add(jbtnGray);
        parent.add(jpnlButtons, BorderLayout.PAGE_START);
        /////////////////////////////////////////
        
        /////////////////////////////////////////
        // JPanel JFreeChart
        // Mostrar gráficos
        jpnlChart = new JPanel(new BorderLayout());        
        jfreeChart = ChartFactory.createXYLineChart("Histograma", "Nível", "Probabilidade", null, PlotOrientation.VERTICAL, true, true, true);    
        xyplot = jfreeChart.getXYPlot();
        chartPanel = new ChartPanel(jfreeChart);
        jpnlChart.add(chartPanel, BorderLayout.CENTER);
        parent.add(jpnlChart, BorderLayout.CENTER);
        customizaCor();
        /////////////////////////////////////////
        
        /////////////////////////////////////////
        // JPanel bottom
        jpnlBottom = new JPanel(new BorderLayout());
        jbtnEqualizar = new JButton("Equalizar");
        jbtnEqualizar.setMaximumSize(new Dimension(100, 25));
        jpnlBottom.add(jbtnEqualizar, BorderLayout.CENTER);
        parent.add(jpnlBottom, BorderLayout.PAGE_END);
        
        setSize(320, 240);
        
        customizaActionListener();
    }
    
    private void customizaActionListener()
    {
        jbtnRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.setSeriesVisible(0, jbtnRed.isSelected());
            }
        });
        jbtnGreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.setSeriesVisible(1, jbtnGreen.isSelected());
            }
        });        
        jbtnBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.setSeriesVisible(2, jbtnBlue.isSelected());
            }
        });        
        jbtnGray.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renderer.setSeriesVisible(3, jbtnGray.isSelected());
            }
        });       
        jbtnEqualizar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JanelaInterna janelinha = new JanelaInterna(janela);
                janela.adicionarJanelaInterna(janelinha);
                janelinha.setAlturaImagem(alturaImg);
                janelinha.setLarguraImagem(larguraImg);
                if (grayScale)
                {
                    int novos_niveis[] = equalizarHistograma(pGray);
                    float nova_imagem[][] = substituirMatriz(novos_niveis, gray);
                    janelinha.setMatrizRed(nova_imagem);
                    janelinha.setMatrizGreen(nova_imagem);
                    janelinha.setMatrizBlue(nova_imagem);
                }
                else                    
                {
                    int novos_niveis_R[] = equalizarHistograma(pRed);
                    float nova_imagem_R[][] = substituirMatriz(novos_niveis_R, red);                    
                    int novos_niveis_G[] = equalizarHistograma(pGreen);
                    float nova_imagem_G[][] = substituirMatriz(novos_niveis_G, green);                    
                    int novos_niveis_B[] = equalizarHistograma(pBlue);
                    float nova_imagem_B[][] = substituirMatriz(novos_niveis_B, blue);  
                    janelinha.setMatrizRed(nova_imagem_R);
                    janelinha.setMatrizGreen(nova_imagem_G);
                    janelinha.setMatrizBlue(nova_imagem_B);                    
                }
                janelinha.setTitle("Equalização de " + title);
                janelinha.criarImagem();
            }
        });
    }
    
    private void customizaCor()
    {
        renderer = new DefaultXYItemRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesPaint(3, Color.BLACK);
        xyplot.setRenderer(renderer);
    } 
               
    private void gerarXYDataSet()
    {       
        xyDataSet = new DefaultXYDataset();
        
        double x[] = new double[256];
        
        for (int c = 0; c <= MAX_RANGE_NIVEIS; c++)
        {
            x[c] = c;
        }
        
        xyDataSet.addSeries("Red", new double[][] {x, pRed});
        xyDataSet.addSeries("Green", new double[][] {x, pGreen});        
        xyDataSet.addSeries("Blue", new double[][] {x, pBlue});
        xyDataSet.addSeries("Gray", new double[][] {x, pGray});
        
        xyplot.setDataset(xyDataSet);
        
        renderer.setSeriesVisible(0, !grayScale);
        renderer.setSeriesVisible(1, !grayScale);
        renderer.setSeriesVisible(2, !grayScale);
        renderer.setSeriesVisible(3, grayScale);
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
    
    private int[] equalizarHistograma(double p[])
    {
        float pAcumulada[] = new float[256];
        int novos_niveis[] = new int[256];        
        pAcumulada[0] = (float) p[0];
        for (int i = 1; i <= MAX_RANGE_NIVEIS; i++)
        {
            pAcumulada[i] = (float) (p[i] + pAcumulada[i - 1]);
        }
        for (int i = 1; i <= MAX_RANGE_NIVEIS; i++)
        {
            novos_niveis[i] = Math.round(255 * pAcumulada[i]);
        }
        return novos_niveis;
    }
    
    private float[][] substituirMatriz(int novos_niveis[], float[][] velha_imagem)
    {
        float nova_imagem[][] = new float[larguraImg][alturaImg];
        for (int i = 0; i < larguraImg; i++)
            for (int j = 0; j < alturaImg; j++)
                nova_imagem[i][j] = novos_niveis[(int) velha_imagem[i][j]];
        return nova_imagem;
    }
    
}


