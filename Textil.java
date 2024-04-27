import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Textil extends Producte implements Comparable<Textil>{
    private String composicio;
    private static List<Textil> productesTextils= new ArrayList<>();
    public Textil(float preu, String nom, int codiBarres, String composicio) {
        super(preu, nom, codiBarres);
        if (!cumpleLongitudRequerida(nom)) throw new IllegalArgumentException("La longitud máxima del nombre debe de ser de 15 caracteres.");
        this.composicio = composicio;
        //Si el codigo de barras está repetido, no lo añade a la lista.
        if (!codigoBarrasRepetido(codiBarres)) {
            productesTextils.add(this);
        }
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
//Ordena según la composicion.
    public int compareTo(Textil t) {
        return this.composicio.compareTo(t.getComposicio());
    }
    private boolean codigoBarrasRepetido (int codiBarres){
    for (Textil textil : productesTextils){
        if (textil.getCodiBarres()==codiBarres) return true;

    }
    return false;
    }
    private boolean cumpleLongitudRequerida(String nom){
        return  getNom().length()<=15;
    }
}
