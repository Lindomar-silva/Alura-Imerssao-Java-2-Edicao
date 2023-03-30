import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Exibir e manipular os dados
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        var http = new ClienteHttp();
        String dados = http.buscaDados(url);
        
        List<Conteudo> conteudos = extrator.extraiConteudo(dados);

        var gerafiguras = new GeradorDeFigurinhas();


        for (int i = 0; i < 3; i++) {
          
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            String texto = "TOPZERA";
            gerafiguras.gerar(inputStream, nomeArquivo, texto); 

            System.out.println("\u001b[1mTítulo:\u001b[0m " + conteudo.getTitulo());
            System.out.println("\u001b[1mPoster\u001b[0m : " + conteudo.getUrlImagem());

            System.out.println();
        }
    }
}
