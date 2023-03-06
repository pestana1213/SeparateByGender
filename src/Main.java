import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) throws Exception {
        FilesParser.teste();
        Map<String,Integer> namesParse = FilesParser.parse();
        ArrayList<String> allNames = FilesParser.getNomes();
        ArrayList<Integer> allNamesOrganized = allNames.stream().map(e->namesParse.getOrDefault(e,-1)).collect(Collectors.toCollection(ArrayList::new));
        //guarda(allNamesOrganized);
        giveOutput(allNamesOrganized);
    }

    public static void guarda(ArrayList<Integer> genero) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\Pestana\\Desktop\\output.txt",false));

        for(Integer i : genero) {
            escritor.write(i.toString()+"\n");
            escritor.flush();
        }
        escritor.close();
    }

    public static void giveOutput(ArrayList<Integer> genero) throws IOException {
        for(Integer i : genero) {
           System.out.println(i);
        }
    }
}