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
        for (int i = 0; i < 20; i++) {
            if (i < 5) {
               new Alimentacio(i + 1, "pera", 1411, dataCaducitat);
            } else if (i >= 5 && i < 10) {
                new Textil(i + 1, "ttttt", 2922, "tttttt");
            } else if (i >= 10 && i < 15) {
                new Textil(i + 1, "Ratas", 2522, "Ratas");
            } else {
               new Alimentacio(i + 1, "plátano", 4404, dataCaducitat);
            }
        }

        t.add(new Textil(11, "Water", 1222, "Water"));
        t.add(new Textil(11, "lana", 2221, "lana"));
        t.add(new Textil(115, "algodon", 55432, "algodon"));
        t.add(new Textil(115, "Raiz", 55432, "algodon"));
        t.add(new Textil(5, "Cucacarcha", 99999, "carne"));

        Collections.sort(t);
        Supermercat.menu();

    }
}
