import java.io.*;
import java.text.*;
import java.util.*;

public class DisneyPlu {
    static class Shows {
        private String show_id;
        private String type;
        private String title;
        private String director;
        private String[] cast;
        private String country;
        private Date date_added;
        private int release_year;
        private String rating;
        private String duration;
        private String[] listed_in;

        private static final SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

        public Shows() {
            this.show_id = this.type = this.title = this.director = this.country = this.rating = this.duration = "NaN";
            this.date_added = null;
            this.release_year = 0;
            this.cast = new String[]{"NaN"};
            this.listed_in = new String[]{"NaN"};
        }

        public void imprimir() {
            System.out.print("=> " + show_id + " ## " +
                             title + " ## " +
                             type + " ## " +
                             director + " ## " +
                             "[" + String.join(", ", cast) + "] ## " +
                             country + " ## " +
                             (date_added != null ? sdf.format(date_added) : "NaN") + " ## " +
                             release_year + " ## " +
                             rating + " ## " +
                             duration + " ## " +
                             "[" + String.join(", ", listed_in) + "] ##\n");
        }

        public void ler(String linha) {
            String[] campos = dividirCSV(linha);

            this.show_id = campoOuNaN(campos, 0);
            this.type = campoOuNaN(campos, 1);
            this.title = campoOuNaN(campos, 2);
            this.director = campoOuNaN(campos, 3);

            String castStr = campoOuNaN(campos, 4);
            if (!castStr.equals("NaN")) {
                cast = castStr.split(", ");
                Arrays.sort(cast);
            } else {
                cast = new String[]{"NaN"};
            }

            this.country = campoOuNaN(campos, 5);

            String dateStr = campoOuNaN(campos, 6);
            try {
                this.date_added = dateStr.equals("NaN") ? null : sdf.parse(dateStr);
            } catch (ParseException e) {
                this.date_added = null;
            }

            this.release_year = campoOuNaN(campos, 7).equals("NaN") ? 0 : Integer.parseInt(campos[7]);
            this.rating = campoOuNaN(campos, 8);
            this.duration = campoOuNaN(campos, 9);

            String listedStr = campoOuNaN(campos, 10);
            if (!listedStr.equals("NaN")) {
                listed_in = listedStr.split(", ");
                Arrays.sort(listed_in);
            } else {
                listed_in = new String[]{"NaN"};
            }
        }

        private String campoOuNaN(String[] campos, int i) {
            if (i >= campos.length || campos[i].trim().isEmpty()) return "NaN";
            return campos[i].trim();
        }

        private String[] dividirCSV(String linha) {
            List<String> tokens = new ArrayList<>();
            boolean aspas = false;
            StringBuilder atual = new StringBuilder();

            for (char c : linha.toCharArray()) {
                if (c == '\"') {
                    aspas = !aspas;
                } else if (c == ',' && !aspas) {
                    tokens.add(atual.toString());
                    atual.setLength(0);
                } else {
                    atual.append(c);
                }
            }

            tokens.add(atual.toString());
            return tokens.toArray(new String[0]);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map<String, String> mapaShows = carregarCSV("/tmp/disneyplus.csv");

        while (true) {
            String entrada = sc.nextLine();
            if (entrada.equals("FIM")) break;

            if (mapaShows.containsKey(entrada)) {
                Shows show = new Shows();
                show.ler(mapaShows.get(entrada));
                show.imprimir();
            }
        }

        sc.close();
    }

    public static Map<String, String> carregarCSV(String caminho) throws IOException {
        Map<String, String> mapa = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha;

        br.readLine(); // cabeçalho

        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split(",", 2);
            if (partes.length >= 2) {
                mapa.put(partes[0], linha);
            }
        }

        br.close();
        return mapa;
    }
}
