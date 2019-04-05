public class Sommet {
    public String nom;
    public String adresse;
    public String type;
    public int age;


    public Sommet() {
        this.nom = null;
        this.type = null;
        this.adresse = null;
        this.age = 0;

    }
    public Sommet(String nom,String type) {
        this.nom = nom;
        this.type = type;
        this.age = 0;
        this.adresse = null;
    }


}
