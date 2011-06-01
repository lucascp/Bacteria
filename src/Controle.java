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

}
