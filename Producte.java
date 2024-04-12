public class Producte {
   private float preu;
   private String nom;
   private int codiBarres;

    public Producte(float preu, String nom, int codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    public float getPreu() {
        return preu;
    }

    public String getNom() {
        return nom;
    }

    public int getCodiBarres() {
        return codiBarres;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCodiBarres(int codiBarres) {
        this.codiBarres = codiBarres;
    }
}
