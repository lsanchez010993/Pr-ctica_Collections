import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataCaducitat = LocalDate.parse("1999-11-11", formatter);
        ArrayList<Textil> t = new ArrayList<>();
        //Datos de prueba:


        t.add(new Textil(11, "Water", 1222, "Water"));
        t.add(new Textil(11, "lana", 2221, "lana"));
        t.add(new Textil(115, "algodon", 55432, "algodon"));
        t.add(new Textil(115, "Raiz", 55432, "algodon"));



        Supermercat.menu();

    }
}
