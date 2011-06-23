/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 969621
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class JanelaMorfoListener implements ActionListener {

    Janela janela;
    JanelaMorfologia janelaMorfo;

    public JanelaMorfoListener(Janela j, JanelaMorfologia j2)
    {
        janela=j;
        janelaMorfo=j2;
    }

    public void actionPerformed(ActionEvent e) {
        int iteracoes = janelaMorfo.isInteger();
        if(iteracoes == -1) {
            janelaMorfo.setTextField("1");
            return;
        }
       
        int elementoEstruturante = janelaMorfo.getComboBox().getSelectedIndex();       
        int matriz_ES[][] = null;        
        if(elementoEstruturante == 0) matriz_ES = new int[][] {{0,1,0}, {1,1,1}, {0,1,0}}; //cruz
        else if(elementoEstruturante == 1)  matriz_ES = new int[][] {{0,0,0}, {1,1,1}, {0,0,0}};   
        else if(elementoEstruturante == 2) matriz_ES = new int[][] {{0,1,0}, {0,1,0}, {0,1,0}};  //horizontal
        else if(elementoEstruturante == 3) matriz_ES = new int[][] {{1,1,1}, {1,1,1}, {1,1,1}};   //square
        else if(elementoEstruturante == 4) matriz_ES = new int[][] {{0,1,1,1,0}, {1,1,1,1,1}, {1,1,1,1,1}, {1,1,1,1,1}, {0,1,1,1,0}}; //Rhombus
        
        if("Erosão".equals(janelaMorfo.tipo)) Controle.erosao(janela.getSelectedFrame(), matriz_ES,  iteracoes); 
        else if("Dilatação".equals(janelaMorfo.tipo)) Controle.dilatacao(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("Abertura".equals(janelaMorfo.tipo)) Controle.abertura(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("Fechamento".equals(janelaMorfo.tipo)) Controle.fechamento(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("Gradiente da Dilatação".equals(janelaMorfo.tipo)) Controle.gradDilatacao(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("Gradiente da Erosão".equals(janelaMorfo.tipo)) Controle.gradErosao(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("TOP-HAT Picos".equals(janelaMorfo.tipo)) Controle.topHatPicos(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        else if("TOP-HAT Vales".equals(janelaMorfo.tipo)) Controle.topHatVales(janela.getSelectedFrame(), matriz_ES, iteracoes); 
        
        janelaMorfo.dispose();


    }

}
