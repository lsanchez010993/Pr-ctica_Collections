public class Electronica extends Producte{
    private int dies_garantia;

    public Electronica(float preu, String nom, int codiBarres, int dies_garantia) {
        super(preu, nom, codiBarres);
        this.dies_garantia = dies_garantia;
    }

    public float getPreu() {
        float preu = super.getPreu();
        return (float) (preu + preu*(dies_garantia/365)*0.1);
    }




}
