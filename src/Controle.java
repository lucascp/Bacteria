//Controle
public class Controle // classe com métodos estáticos para realizar as operacoes de Imagem
{
    private static Janela janela;

    public Controle(Janela janela)
    {
        this.janela=janela;
    }


    public static void escalaDeCinza(JanelaInterna jan)//cria uma imagem em escala de ciza da imagem selecionada
    {
        float red[][] = jan.getRed();
        float green[][] = jan.getGreen();
        float blue[][] = jan.getBlue();
        
        float cinza[][] = new float[jan.getLarguraImagem()][jan.getAlturaImagem()];
        
        float matriz[][]= {{0.11f,0.11f,0.11f},
                            {0.11f,0.11f,0.11f},
                            {0.11f,0.11f,0.11f}};
        

        
        for(int i=0;i<jan.getLarguraImagem();i++)
        {
            for(int j=0;j<jan.getAlturaImagem();j++)
            {
                cinza[i][j]=0.299f*red[i][j] + 0.587f*green[i][j] + 0.114f*blue[i][j];
            }
        }


        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        novaJanelaInterna.setMatrizBlue(cinza);
        novaJanelaInterna.setMatrizRed(cinza);
        novaJanelaInterna.setMatrizGreen(cinza);
        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());

        novaJanelaInterna.setTitle("Escala de cinza da "+jan.getTitle());

        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    
    
    public static void soma(JanelaInterna j1, JanelaInterna j2)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();

        float matrizBlue[][]=somaMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=somaMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=somaMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("soma da "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }    
    public static void subtrai(JanelaInterna j1, JanelaInterna j2)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();

        float matrizBlue[][]=subtraiMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=subtraiMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=subtraiMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("subtracao da "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }
    public static void or(JanelaInterna j1, JanelaInterna j2)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();

        float matrizBlue[][]=orMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=orMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=orMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("subtracao da "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    public static void zoomIn(JanelaInterna j1)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        int largura = j1.getLarguraImagem();
        int altura = j1.getAlturaImagem();

        float matrizBlue[][]=zoomIn(j1.getBlue(), largura, altura);
        float matrizRed[][]=zoomIn(j1.getRed(), largura, altura);
        float matrizGreen[][]=zoomIn(j1.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura*2);
        novaJanelaInterna.setAlturaImagem(altura*2);
        novaJanelaInterna.setTitle("Zoom in de " +j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    public static void zoomOut(JanelaInterna j1)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        int largura = j1.getLarguraImagem();
        int altura = j1.getAlturaImagem();

        float matrizBlue[][]=zoomOut(j1.getBlue(), largura, altura);
        float matrizRed[][]=zoomOut(j1.getRed(), largura, altura);
        float matrizGreen[][]=zoomOut(j1.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura/2);
        novaJanelaInterna.setAlturaImagem(altura/2);
        novaJanelaInterna.setTitle("Zoom out de " +j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    
    public static void nor(JanelaInterna j1, JanelaInterna j2)
    {        
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();

        float matrizBlue[][]=norMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=norMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=norMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("subtracao da "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }
    public static void and(JanelaInterna j1, JanelaInterna j2)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();
        
        float matrizBlue[][]=andMatriz(j1.getBlue(), j2.getBlue(),largura, altura);
        float matrizRed[][]=andMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=andMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("and de "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }    
    public static void nand(JanelaInterna j1, JanelaInterna j2){
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();
        

        float matrizBlue[][]=nandMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=nandMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=nandMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("subtracao da "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }
    public static void xor(JanelaInterna j1, JanelaInterna j2){
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        int largura = j1.getLarguraImagem()>j2.getLarguraImagem()?j2.getLarguraImagem():j1.getLarguraImagem();
        int altura = j1.getAlturaImagem()>j2.getAlturaImagem()?j2.getAlturaImagem():j1.getAlturaImagem();

        float matrizBlue[][]=xorMatriz(j1.getBlue(), j2.getBlue(), largura, altura);
        float matrizRed[][]=xorMatriz(j1.getRed(), j2.getRed(), largura, altura);
        float matrizGreen[][]=xorMatriz(j1.getGreen(), j2.getGreen(), largura, altura);

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("xor de "+j1.getTitle()+" com "+j2.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }
    public static void not(JanelaInterna j1)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        float matrizBlue[][]=j1.getBlue();
        float matrizRed[][]=j1.getRed();
        float matrizGreen[][]=j1.getGreen();
        
        for(int i=0;i<j1.getLarguraImagem();i++)
        {
            for(int j=0;j<j1.getAlturaImagem();j++)
            {
                matrizBlue[i][j]=255-matrizBlue[i][j];
                matrizGreen[i][j]=255-matrizGreen[i][j];
                matrizRed[i][j]=255-matrizRed[i][j];
            }
        }

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(j1.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(j1.getAlturaImagem());
        novaJanelaInterna.setTitle("Not de "+j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);

    }

    public static void limiarBinario(JanelaInterna j1, int limiar)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        float matrizBlue[][]=j1.getBlue();
        float matrizRed[][]=j1.getRed();
        float matrizGreen[][]=j1.getGreen();

        for(int i=0;i<j1.getLarguraImagem();i++)
        {
            for(int j=0;j<j1.getAlturaImagem();j++)
            {
                matrizBlue[i][j]=matrizBlue[i][j]>=limiar?255:0;
                matrizGreen[i][j]=matrizGreen[i][j]>=limiar?255:0;
                matrizRed[i][j]=matrizRed[i][j]>=limiar?255:0;
            }
        }

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(j1.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(j1.getAlturaImagem());
        novaJanelaInterna.setTitle("Limiarizacao de " +limiar+" em " + j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    public static void limiarToWhite(JanelaInterna j1, int limiar)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        float matrizBlue[][]=j1.getBlue();
        float matrizRed[][]=j1.getRed();
        float matrizGreen[][]=j1.getGreen();

        for(int i=0;i<j1.getLarguraImagem();i++)
        {
            for(int j=0;j<j1.getAlturaImagem();j++)
            {
                matrizBlue[i][j]=matrizBlue[i][j]>=limiar?255:matrizBlue[i][j];
                matrizGreen[i][j]=matrizGreen[i][j]>=limiar?255:matrizGreen[i][j];
                matrizRed[i][j]=matrizRed[i][j]>=limiar?255:matrizRed[i][j];
            }
        }

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(j1.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(j1.getAlturaImagem());
        novaJanelaInterna.setTitle("Limiarizacao de " +limiar+" em " + j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    public static void limiarToBlack(JanelaInterna j1, int limiar)
    {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);

        float matrizBlue[][]=j1.getBlue();
        float matrizRed[][]=j1.getRed();
        float matrizGreen[][]=j1.getGreen();

        for(int i=0;i<j1.getLarguraImagem();i++)
        {
            for(int j=0;j<j1.getAlturaImagem();j++)
            {
                matrizBlue[i][j]=matrizBlue[i][j]<=limiar?0:matrizBlue[i][j];
                matrizGreen[i][j]=matrizGreen[i][j]<=limiar?0:matrizGreen[i][j];
                matrizRed[i][j]=matrizRed[i][j]<=limiar?0:matrizRed[i][j];
            }
        }

        novaJanelaInterna.setMatrizBlue(matrizBlue);
        novaJanelaInterna.setMatrizRed(matrizRed);
        novaJanelaInterna.setMatrizGreen(matrizGreen);
        novaJanelaInterna.setLarguraImagem(j1.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(j1.getAlturaImagem());
        novaJanelaInterna.setTitle("Limiarizacao de " +limiar+" em " + j1.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }






    /*
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */


    
    //MÉTODOS PRIVADOS (AUXILIARES)
    
    private static float[][] somaMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
                matriz[i][j]=m1[i][j] + m2[i][j];
                if(matriz[i][j]>255) matriz[i][j]=255;
            }

        }
        return matriz;
    }    
    private static float[][] subtraiMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
                matriz[i][j]=m1[i][j] - m2[i][j];
                if(matriz[i][j]<0) matriz[i][j]=0;
            }

        }
        return matriz;
    }
    private static float[][] orMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
               // matriz[i][j]=(m1[i][j]>m2[i][j])?m1[i][j]:m2[i][j];
                matriz[i][j] = (((int)m1[i][j] | (int)m2[i][j]) & 0xFF);
            }

        }
        return matriz;
    }
    private static float[][] norMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
                //matriz[i][j]=255 - ((m1[i][j]>m2[i][j])?m1[i][j]:m2[i][j]);
                matriz[i][j] = (~((int)m1[i][j] | (int)m2[i][j]) & 0xFF);
            }

        }
        return matriz;
    }
    private static float[][] xorMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];

        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
               // matriz[i][j]=(m1[i][j]>m2[i][j])?m1[i][j]:m2[i][j];
                matriz[i][j] = (((int)m1[i][j] ^ (int)m2[i][j]) & 0xFF);
            }

        }
        return matriz;
    }
    private static float[][] andMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
                //matriz[i][j]=(m1[i][j]>m2[i][j])?m2[i][j]:m1[i][j];
                matriz[i][j] = (((int)m1[i][j] & (int)m2[i][j]) & 0xFF);
            }

        }
        return matriz;
    }
    private static float[][] nandMatriz(float m1[][], float m2[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX][tamY];
        
        for(int i=0;i<tamX;i++)
        {
            for(int j=0;j<tamY;j++)
            {
               // matriz[i][j]=255-((m1[i][j]>m2[i][j])?m2[i][j]:m1[i][j]);
                matriz[i][j] = (~((int)m1[i][j] & (int)m2[i][j]) & 0xFF);
            }

        }
        return matriz;
    }
    private static float[][] zoomIn(float m1[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX*2][tamY];
        float matriz2[][] = new float[tamX*2][tamY*2];
        
        for(int i=1;i<tamX;i=i+1)
        {
            for(int j=1;j<tamY;j++)
            {
                matriz[2*i][j]=m1[i][j];
                matriz[2*i-1][j]=m1[i][j];                

            }
        }
        for(int i=1;i<tamX*2;i=i+1)
        {
            for(int j=1;j<tamY;j++)
            {
                matriz2[i][2*j]=matriz[i][j];
                matriz2[i][2*j-1]=matriz[i][j];

            }
        }
        
        return matriz2;
    }
    private static float[][] zoomOut(float m1[][], int tamX, int tamY) // Por enquanto as matrizes tem que ser do mesmo tamanho
    {
        float matriz[][] = new float[tamX/2][tamY];
        float matriz2[][] = new float[tamX*2][tamY*2];

        for(int i=1;i<tamX/2;i=i+1)
        {
            for(int j=1;j<tamY;j++)
            {
                matriz[i][j]=(m1[2*i][j]+m1[2*i-1][j])/2;
            }
        }
        for(int i=1;i<tamX/2;i=i+1)
        {
            for(int j=1;j<tamY/2;j++)
            {
                matriz2[i][j]=(matriz[i][2*j]+matriz[i][2*j-1])/2;

            }
        }

        return matriz2;
    }
    
    
 private static float[][][] erosaoFunction(float matriz[][][], int[][] elementoEstruturante, int iteracoes) {
        /*
        * Erosao feita apenas com elementos planares.
        */
        int es_largura = elementoEstruturante.length;
        int es_altura = elementoEstruturante[0].length;
        
        int largura = matriz[0].length;
        int altura = matriz[0][0].length;

        float matriz_resultado[][][] = new float[3][largura][altura];        
        float max = 0;
        
              
        /*So para colocar as bordas*/
       for (int i = 0; i < largura; i++) {
            for(int j = 0; j < altura; j++) {
                matriz_resultado[0][i][j] = matriz[0][i][j];
                matriz_resultado[1][i][j] = matriz[0][i][j];
                matriz_resultado[2][i][j] = matriz[0][i][j];
            }             
        }      
       
        for(int it = 0; it < iteracoes; it++) {        
            for(int canal = 0; canal < 3; canal++) {            
                for(int jlargura = es_largura/2; jlargura < (largura-es_largura/2); jlargura++) {
                    for(int jaltura = es_altura/2; jaltura < (altura-es_altura/2); jaltura++) {
                        for(int i = 0; i < es_largura; i++) {
                            for(int j = 0; j< es_altura; j++)
                            {
                                /*
                                 * Movendo o elemento estruturante em cada matriz.
                                 * O algoritmo para imagem binária e em escala de cinza é igual!
                                 * Para erosão, eu verifico o maximo(lembrando que branco é 255 e preto é 0) entre os pontos pertencentes ao elemento estruturante
                                 */                                
                                 if(elementoEstruturante[i][j] == 1 && (matriz[canal][jlargura-es_largura/2+i][jaltura-es_altura/2+j] > max)) {
                                     max = matriz[canal][jlargura-es_largura/2+i][jaltura-es_altura/2+j];
                                 }
                            } //fim es_altura
                        } // fim es_largura
                        matriz_resultado[canal][jlargura][jaltura] = max; 
                        max = 0;
                    } //fim matriz altura
                } // fim matriz largura
            } // fim canal   
            
            for (int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    matriz[0][i][j] = matriz_resultado[0][i][j];
                    matriz[1][i][j] = matriz_resultado[1][i][j];
                    matriz[2][i][j] = matriz_resultado[2][i][j];
                }             
            }        
        } // fim iteração
        
        return matriz_resultado;        
    }
    
    public static void erosao(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes){
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
        matriz = erosaoFunction(matriz, elementoEstruturante, iteracoes);
        
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);

        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("erosão (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
    
    private static float[][][] dilatacaoFunction(float matriz[][][], int[][] elementoEstruturante, int iteracoes) {
                /*
        * Dilatação feita apenas com elementos planares.
        */
        int es_largura = elementoEstruturante.length;
        int es_altura = elementoEstruturante[0].length;
        
        int largura = matriz[0].length;
        int altura = matriz[0][0].length;
                
        float matriz_resultado[][][] = new float[3][largura][altura];        
        float min = 255;
        
        /*So para colocar as bordas*/
       for (int i = 0; i < largura; i++) {
            for(int j = 0; j < altura; j++) {
                matriz_resultado[0][i][j] = matriz[0][i][j];
                matriz_resultado[1][i][j] = matriz[0][i][j];
                matriz_resultado[2][i][j] = matriz[0][i][j];
            }             
        }    
                       
        for(int it = 0; it < iteracoes; it++) {        
            for(int canal = 0; canal < 3; canal++) {            
                for(int jlargura = es_largura/2; jlargura < (largura-es_largura/2); jlargura++) {
                    for(int jaltura = es_altura/2; jaltura < (altura-es_altura/2); jaltura++) {
                        for(int i = 0; i < es_largura; i++) {
                            for(int j = 0; j< es_altura; j++)
                            {
                                /*
                                 * Movendo o elemento estruturante em cada matriz.
                                 * O algoritmo para imagem binária e em escala de cinza é igual!
                                 * Para dilatação, eu verifico o minimo(lembrando que branco é 255 e preto é 0) entre os pontos pertencentes ao elemento estruturante
                                 */                                
                                 if(elementoEstruturante[i][j] == 1 && (matriz[canal][jlargura-es_largura/2+i][jaltura-es_altura/2+j] < 255)) {
                                     min = matriz[canal][jlargura-es_largura/2+i][jaltura-es_altura/2+j];
                                 }
                            } //fim es_altura
                        } // fim es_largura
                        matriz_resultado[canal][jlargura][jaltura] = min; 
                        min = 255;
                    } //fim matriz altura
                } // fim matriz largura
            } // fim canal   
            
            for (int i = 0; i < largura; i++) {
                for(int j = 0; j < altura; j++) {
                    matriz[0][i][j] = matriz_resultado[0][i][j];
                    matriz[1][i][j] = matriz_resultado[1][i][j];
                    matriz[2][i][j] = matriz_resultado[2][i][j];
                }             
            }        
        } // fim iteração
        
        return matriz_resultado;
    }
    
     public static void dilatacao(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes){
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
        int largura = jan.getLarguraImagem();
        int altura = jan.getAlturaImagem();
        
        matriz = dilatacaoFunction(matriz, elementoEstruturante, iteracoes); 
        
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);

        novaJanelaInterna.setLarguraImagem(largura);
        novaJanelaInterna.setAlturaImagem(altura);
        novaJanelaInterna.setTitle("dilatação (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
     
    public static void abertura(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes){
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        //erodir i vezes e depois dilatar i vezes, onde i = iterações
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
        matriz = erosaoFunction(matriz, elementoEstruturante, iteracoes);
        matriz = dilatacaoFunction(matriz, elementoEstruturante, iteracoes);
                
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);

        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("abertura (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);         
    }
    
    public static void fechamento(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes) {
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        
        //erodir i vezes e depois dilatar i vezes, onde i = iterações
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
        matriz = dilatacaoFunction(matriz, elementoEstruturante, iteracoes);
        matriz = erosaoFunction(matriz, elementoEstruturante, iteracoes);
        
                
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);

        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("fechamento (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);  
    }
    
    public static void gradDilatacao(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes){
        // matriz dilatada - matriz
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();   
        float matrizDilatada[][][] = new float[3][][];
        matrizDilatada = dilatacaoFunction(matriz, elementoEstruturante, iteracoes);
        
        for(int canal = 0; canal < 3; canal++) {
            for(int i = 0; i < jan.getLarguraImagem(); i++) {
                for(int j = 0; j < jan.getAlturaImagem(); j++) {
                    matriz[canal][i][j] = matrizDilatada[canal][i][j] + matriz[canal][i][j];
                    if(matriz[canal][i][j] > 255) matriz[canal][i][j] = 255;
                    
                    
                }
            }
        }
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);
        
        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("grad dilatacao(it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);  
    }
    
    public static void gradErosao(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes) {
        //bordas aprimoradas->  matriz - (erosao da matriz) 
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen(); 
        
        float matrizErodida[][][] = erosaoFunction(matriz, elementoEstruturante, iteracoes);
        
        for(int canal = 0; canal < 3; canal++) {
            for(int i = 0; i < jan.getLarguraImagem(); i++) {
                for(int j = 0; j < jan.getAlturaImagem(); j++) {
                    matriz[canal][i][j] = matriz[canal][i][j] + matrizErodida[canal][i][j];
                    if(matriz[canal][i][j] > 255) matriz[canal][i][j] = 255;
                }
            }
        }
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);
        
        
        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("grad erosao (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);             
    }
    
    public static void topHatPicos(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes) {
        //matriz - abertura da matriz 
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
                
        float matrizAbertura[][][] = erosaoFunction(matriz, elementoEstruturante, iteracoes);
        matrizAbertura = dilatacaoFunction(matrizAbertura, elementoEstruturante, iteracoes);
        
                for(int canal = 0; canal < 3; canal++) {
            for(int i = 0; i < jan.getLarguraImagem(); i++) {
                for(int j = 0; j < jan.getAlturaImagem(); j++) {
                    matriz[canal][i][j] = matriz[canal][i][j] - matrizAbertura[canal][i][j];
                    if(matriz[canal][i][j] < 0) matriz[canal][i][j] = 0;
                }
            }
        }
                
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);
        
        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("topHatPicos (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);             
    }
    
    public static void topHatVales(JanelaInterna jan, int[][] elementoEstruturante, int iteracoes) {
        // fechamento da matriz - matriz 
        JanelaInterna novaJanelaInterna = new JanelaInterna(janela);
        float matriz[][][] = new float[3][][];
        matriz[0] = jan.getRed();
        matriz[1] = jan.getBlue();
        matriz[2] = jan.getGreen();
        
        float matrizFechamento[][][] = dilatacaoFunction(matriz, elementoEstruturante, iteracoes);
        matrizFechamento = erosaoFunction(matrizFechamento, elementoEstruturante, iteracoes);
        
         for(int canal = 0; canal < 3; canal++) {
            for(int i = 0; i < jan.getLarguraImagem(); i++) {
                for(int j = 0; j < jan.getAlturaImagem(); j++) {
                    matriz[canal][i][j] = matrizFechamento[canal][i][j] - matriz[canal][i][j];
                    if(matriz[canal][i][j] < 0) matriz[canal][i][j] = 0;
                }
            }
        } 
        novaJanelaInterna.setMatrizRed(matriz[0]);
        novaJanelaInterna.setMatrizBlue(matriz[1]);
        novaJanelaInterna.setMatrizGreen(matriz[2]);
        
        novaJanelaInterna.setLarguraImagem(jan.getLarguraImagem());
        novaJanelaInterna.setAlturaImagem(jan.getAlturaImagem());
        novaJanelaInterna.setTitle("topHatVales (it: "+iteracoes +") da " +jan.getTitle());
        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);          
    }

}
