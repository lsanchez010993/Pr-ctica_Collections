import java.util.ArrayList;
import java.util.List;

public class Electronica extends Producte{
    private int dies_garantia;
    private static List<Electronica> productesElectronics = new ArrayList<>();

    public Electronica(float preu, String nom, int codiBarres, int dies_garantia) {
        super(preu, nom, codiBarres);
        if (!cumpleLongitudRequerida(nom)) throw new IllegalArgumentException("La longitud máxima del nombre debe de ser de 15 caracteres.");
        this.dies_garantia = dies_garantia;
        productesElectronics.add(this);
    }

    public float getPreu() {
        float preu = super.getPreu();
        return (float) (preu + preu*(dies_garantia/365)*0.1);
    }

    public static List<Electronica> getProductesElectronics() {
        return productesElectronics;
    }
    private boolean cumpleLongitudRequerida(String nom){
        return  getNom().length()<=15;
    }
}
