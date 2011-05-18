


public class Controle // classe com métodos estáticos para realizar as operacoes de Imagem
{
    private static Janela janela;

    public Controle(Janela janela)
    {
        this.janela=janela;
    }


    public static void escalaDeCiza(JanelaInterna jan)//cria uma imagem em escala de ciza da imagem selecionada
    {
        float red[][] = jan.getRed();
        float green[][] = jan.getGreen();
        float blue[][] = jan.getBlue();
        
        float cinza[][] = new float[jan.getLarguraImagem()][jan.getAlturaImagem()];


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

        novaJanelaInterna.criarImagem();
        janela.adicionarJanelaInterna(novaJanelaInterna);
    }
}
