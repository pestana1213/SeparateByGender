import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesParser {

    public static Map<String, Integer> parse() throws Exception {
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\nomes.txt");
        String[] linhaPartida;
        Map<String,Integer> isMaleOrFemale = new HashMap<>(); //0 para masculino, 1 para feminino
        int gender = -1;

        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]){
                case("masculino"):
                    gender = 0;
                    break;
                case("feminino"):
                    gender = 1;
                    break;
                default:
                    String[] nome = linhaPartida[0].split(" "); //fica so com o primeiro nome
                    if(gender!=-1){
                        isMaleOrFemale.put(linhaPartida[0],gender);
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
        List<String> linhas = lerFicheiro("C:\\Users\\Pestana\\Desktop\\todos.txt");
        String[] linhaPartida;
        ArrayList<String> nomes = new ArrayList<>();
        for (String linha : linhas) {
            linhaPartida = linha.split(" "); //fica so com o primeiro nome
            nomes.add(linhaPartida[0]);
        }

        return nomes;
    }

    public static List<String> lerFicheiro(String nomeFich) {
            List<String> lines;
            try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
            catch(IOException exc) { lines = new ArrayList<>(); }
            return lines;
    }
}
