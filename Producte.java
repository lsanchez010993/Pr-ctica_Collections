public abstract class Producte {
   private float preu;
   private String nom;
   private int codiBarres;
   private int cantidad;


    public Producte(float preu, String nom, int codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
        this.cantidad=1;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
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
