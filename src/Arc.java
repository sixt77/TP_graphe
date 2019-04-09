public class Arc {
	public Sommet origine;
	public Sommet fin;
	public String type;
	public String valeur;

	public Arc(Sommet origine, Sommet fin, String type) {
		this.origine = origine;
		this.fin = fin;
		this.type = type;
	}
	public Arc(Sommet origine, Sommet fin, String type, String valeur) {
		this.origine = origine;
		this.fin = fin;
		this.type = type;
		this.valeur = valeur;
	}

}
