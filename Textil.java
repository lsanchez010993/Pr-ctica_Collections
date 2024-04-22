import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Textil extends Producte implements Comparable<Textil>{
    private String composicio;
    private static List<Textil> productesTextils= new ArrayList<>();
    public Textil(float preu, String nom, int codiBarres, String composicio) {
        super(preu, nom, codiBarres);
        this.composicio = composicio;
        productesTextils.add(this);
    }

    public String getComposicio() {
        return composicio;
    }

    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }

    public static List<Textil> getProductesTextils() {
        return productesTextils;
    }

    public int compareTo(Textil t) {
        return this.composicio.compareTo(t.getComposicio());
    }

}
