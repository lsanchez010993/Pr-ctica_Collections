import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Alimentacio extends Producte {
    private LocalDate dataCaducitat;
    private static List<Alimentacio> productesAlimentacio = new ArrayList<>();



    public Alimentacio(float preu, String nom, int codiBarres, LocalDate data) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = data;
        productesAlimentacio.add(this);



    }



    @Override
    public float getPreu() {

        float preu = super.getPreu();
        LocalDate dataActual = LocalDate.now();

        long diferencia = dataActual.until(dataCaducitat).getDays();
        return preu - (float) (preu * (1 / (diferencia + 1)) + (preu * (0.1)));
    }

    public static List<Alimentacio> getProductesAlimentacio() {
        return productesAlimentacio;
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }
}
