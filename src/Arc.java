public class Arc {
	public Sommet origine;
	public Sommet fin;
	public boolean is_A;
	public boolean est_Ami;
	public Arc(Sommet origine, Sommet fin) {
		this.origine = origine;
		this.fin = fin;
		this.is_A = false;
		this.est_Ami = false;
	}

}
