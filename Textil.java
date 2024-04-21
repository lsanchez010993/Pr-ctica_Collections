public class Textil extends Producte implements Comparable<Textil>{
    private String composicio;

    public Textil(float preu, String nom, int codiBarres, String composicio) {
        super(preu, nom, codiBarres);
        this.composicio = composicio;
    }

    public String getComposicio() {
        return composicio;
    }

    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }



    public int compareTo(Textil t) {
        return this.composicio.compareTo(t.getComposicio());
    }

}
