import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilesParser {

    public static Map<String, Integer> parse() throws Exception {
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\barbara\\sera.txt");
        String[] linhaPartida;
        Map<String,Integer> isMaleOrFemale = new HashMap<>(); //0 para masculino, 1 para feminino
        int gender = -1;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]){
                case("masculino"):
                    gender = 2;
                    break;
                case("feminino"):
                    gender = 1;
                    break;
                default:
                    String[] nome = linhaPartida[0].split(" "); //fica so com o primeiro nome
                    if(gender!=-1){
                        isMaleOrFemale.put(nome[0],gender);
                    }
                    else{
                        throw new Exception("algo de errado nao esta certo");
                    }
                    break;
            }
        }
        return isMaleOrFemale;
    }

    public static ArrayList<String> getNomes() {
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\barbara\\todos.txt");
        String[] linhaPartida;
        ArrayList<String> nomes = new ArrayList<>();
        for (String linha : linhas) {
            linhaPartida = linha.split(" "); //fica so com o primeiro nome
            nomes.add(linhaPartida[0]);
        }
        return nomes;
    }

    public static void teste() throws IOException {
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\barbara\\teste.txt");
        String[] linhaPartida;
        Map<String,Integer> mapa = new HashMap<>();
        for (String linha : linhas) {
            linhaPartida = linha.split("\t");
            String[] nomee = linhaPartida[0].split(" ");
            String nome = nomee[0];
            String gender = linhaPartida[1];  //sabe se é masculino ou feminino
            if(gender.equals("feminino")){
                mapa.put(nome,1);
            }
            else{
                mapa.put(nome,0);
            }
        }
        guarda(mapa);
    }

    public static void guarda(Map<String, Integer> genero) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\Pestana\\Desktop\\barbara\\sera.txt",false));
        ArrayList<String> homens = genero.keySet().stream().filter(e->genero.get(e)==2).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> mulheres = genero.keySet().stream().filter(e->genero.get(e)==1).collect(Collectors.toCollection(ArrayList::new));
        escritor.write("masculino:\n");
        escritor.flush();
        for(String homem : homens) {
            escritor.write(homem +"\n");
            escritor.flush();
        }
        escritor.write("feminino:\n");
        escritor.flush();
        for(String mulher : mulheres) {
            escritor.write(mulher +"\n");
            escritor.flush();
        }

        escritor.close();
    }

    public static List<String> lerFicheiro(String nomeFich) {
            List<String> lines;
            try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
            catch(IOException exc) { lines = new ArrayList<>(); }
            return lines;
    }
}
