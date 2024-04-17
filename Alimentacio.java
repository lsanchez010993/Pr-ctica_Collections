import java.time.LocalDate;
import java.util.ArrayList;


public class Alimentacio extends Producte {
    private LocalDate dataCaducitat;
    private ArrayList<Alimentacio> alimentacio = new ArrayList<>();


    public Alimentacio(float preu, String nom, int codiBarres, LocalDate data) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = data;
        alimentacio.add(new Alimentacio(preu, nom, codiBarres, data));


    }



    @Override
    public float getPreu() {

        float preu = super.getPreu();
        LocalDate dataActual = LocalDate.now();

        long diferencia = dataActual.until(dataCaducitat).getDays();
        return preu - (float) (preu * (1 / (diferencia + 1)) + (preu * (0.1)));
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }
}
