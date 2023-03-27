import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 filmes

        // String imdbKey = System.getenv("VARIAVEL_DE_AMBIENTE");
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body =  response.body();

        // Extrair só os dados (titulo, poster, classificação) que interessam no IMDB-API 
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1mTítulo:\u001b[0m " + filme.get("title"));
            System.out.println("\u001b[1mPoster\u001b[0m : " + filme.get("image"));
            System.out.println("\u001b[1mClassificação:\u001b[0m " + filme.get("imDbRating"));

            double doubleRating = Double.parseDouble(filme.get("imDbRating"));
            int estrelas = (int) doubleRating;

            for (int n = 1; n <= estrelas; n++) {
                System.out.print("⭐");
            }

            System.out.println();
        }
    }
}
