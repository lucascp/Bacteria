
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
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
    private JButton jbtnRed;
    private JButton jbtnGreen;
    private JButton jbtnBlue;
    private JButton jbtnGray;
    private JPanel jpnlChart;
    private ChartPanel chartPanel;
    private JFreeChart jfreeChart;
    private XYPlot xyplot;
    private DefaultXYDataset xyDataSet;  
    
    public JanelaHistograma(Janela janela, float red[][], float green[][], float blue[][], float gray[][], int alturaImg, int larguraImg)
    {
        this.janela = janela;
        this.alturaImg = alturaImg;
        this.larguraImg = larguraImg;
        this.red = red;
        this.green = green;
        this.blue = blue;
        
        //////////////////////////////////////////
        // Constrói interface
        construirInterface();
        //////////////////////////////////////////
        
        /////////////////////////////////////////
        // Operações com histograma
        histogramaRGB();
        /////////////////////////////////////////
        
        /////////////////////////////////////////
        // Plotagem dos gráficos
        gerarXYDataSet();
        /////////////////////////////////////////
        
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
        jbtnRed = new JButton("Red");
        jbtnGreen = new JButton("Green");
        jbtnBlue = new JButton("Blue");
        jbtnGray = new JButton("Gray");
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
        
        setSize(800, 640);
    }
    
    private void customizaCor()
    {
        XYItemRenderer renderer = new DefaultXYItemRenderer();
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


