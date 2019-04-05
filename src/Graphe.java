import java.util.*;

public class Graphe {
	public ArrayList<Sommet> G;
	public ArrayList<Arc> A;
	public Graphe() {
		this.G = new ArrayList();
		this.A = new ArrayList();
	}
	public void ajouterSommet(Sommet noeud) {
		if(chercherSommet(noeud.nom) == -1) {
			this.G.add(noeud);
		}else {
			System.out.println("ce noeud existe déja");
		}
		System.out.println(this.G.size());

	}
	public void ajouterArc(Arc arc) {
		this.A.add(arc);
	}

	public int chercherSommet(String nom) {
		boolean find = false;
		int rank = 0;
		for(int i=0; i<this.G.size(); i++) {
			if(this.G.get(i).nom.equals(nom)) {
				find = true;
				rank = i;
			}
		}
		if(find) {
			return rank;
		}else {
			return -1;
		}
	}
	public void afficherListeSommet() {
		if(this.G.size() > 0) {
			System.out.println("liste des sommets : ");
			for(int i=0; i<this.G.size(); i++) {
				System.out.println(this.G.get(i).nom + "  " + this.G.get(i).type + "  " + this.G.get(i).age + "  " + this.G.get(i).adresse );
			}
		}else {
			System.out.println("liste vide");
		}
	}
	public void afficherListeArc() {
		if(this.A.size() > 0) {
			System.out.println("liste des Arc : ");
			for(int i=0; i<this.A.size(); i++) {
				if(this.A.get(i).is_A == true)
					System.out.println("origine : "+this.A.get(i).origine.nom+" -> "+this.A.get(i).fin.nom + " relation : " +this.A.get(i).origine.nom + " est un "+this.A.get(i).fin.nom );


			}
		}else {
			System.out.println("liste vide");
		}
	}
	public Sommet selectionnerSommet() {
		Boolean trouve1 = false;
		Scanner sc = new Scanner(System.in);
		Sommet noeud1 = null;
		while(trouve1 == false) {
			this.afficherListeSommet();
			System.out.println("Veuillez choisir un sommet :");
			String nom1 = sc.nextLine();
			if(this.chercherSommet(nom1) != -1) {
				noeud1 = this.G.get(this.chercherSommet(nom1));
				trouve1 = true;
			}else {
				System.out.println("le sommet n'existe pas dans le graphe");
			}
		}
		return noeud1;
	}
	public int chercherChemin(Sommet noeud1, Sommet noeud2) {
		return 0;

	}
	public ArrayList<Sommet> chercherVoisin(Sommet noeud1){
		ArrayList<Sommet> listeVoisin = null;
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.nom.equals(noeud1.nom)) {
				listeVoisin.add(this.A.get(i).origine);
			}
			if(this.A.get(i).fin.nom.equals(noeud1.nom)) {
				listeVoisin.add(this.A.get(i).fin);
			}
		}
		return listeVoisin;
	}


	public Boolean propagation(Sommet noeud1, Sommet noeud2){
		int i = 1;
		int j = 0;

		ArrayList<Sommet> liste1 = new ArrayList<Sommet>();
		liste1.add(noeud1);

		while(i != j) {
			i = liste1.size();
			for(int x=0; x<liste1.size(); x++) {
				liste1 = this.chercherVoisin(liste1.get(x), liste1);
			}
			j = liste1.size();
		}
		if(liste1.indexOf(noeud2) != -1) {
			return true;
		}else {
			return false;
		}

	}

	public ArrayList<Sommet> chercherVoisin(Sommet noeud1, ArrayList<Sommet> liste1){
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.nom.equals(noeud1.nom)) {
				if(liste1.indexOf(this.A.get(i).fin)==-1) {
					liste1.add(this.A.get(i).fin);
				}
			}
		}
		return liste1;
	}

	public ArrayList<Sommet> chercherVoisin(ArrayList<Sommet> liste1, ArrayList<Sommet> liste2){
		ArrayList<Sommet> listeVoisin = null;
		for(int i=0; i<liste1.size(); i++) {
			for(int j=0; j<this.A.size(); j++) {
				if(this.A.get(i).origine.nom.equals(liste1.get(i))) {
					if(liste1.indexOf(this.A.get(j).origine)==-1) {
						listeVoisin.add(this.A.get(j).origine);
					}
				}
				if(this.A.get(i).fin.nom.equals(liste1.get(i))) {
					if(liste1.indexOf(this.A.get(j).fin)==-1) {
						listeVoisin.add(this.A.get(j).fin);
					}
				}
			}
		}
		return listeVoisin;
	}

	public ArrayList<Sommet> fusionArrayList(ArrayList<Sommet> liste1, ArrayList<Sommet> liste2) {
		ArrayList<Sommet> liste3 = null;
		for(int i=0; i<liste1.size(); i++) {
			liste3.add(liste1.get(i));
		}
		for(int i=0; i<liste2.size(); i++) {
			liste3.add(liste2.get(i));
		}
		return liste3;
	}

	public int lemmeDeKoening(Sommet noeud1, Sommet noeud2) {
		ArrayList<Sommet> listSommet = null;
		ArrayList<Sommet> listNouveauSommet = null;
		listSommet.add(noeud1);
		listNouveauSommet.add(noeud1);
		while(listSommet.indexOf(noeud2) == -1) {
			for(int i = 0; i< listNouveauSommet.size(); i++) {
				listNouveauSommet = this.chercherVoisin(listNouveauSommet.get(i), listSommet);
			}

		}
		return 0;
	}
	public boolean cheminEntre2Sommet(Sommet noeud1, Sommet noeud2) {
		boolean find = false;
		for(int i=0; i<this.A.size(); i++) {
			if((this.A.get(i).origine.nom.equals(noeud1.nom)  && this.A.get(i).fin.nom.equals(noeud2.nom))||(this.A.get(i).fin.nom.equals(noeud1.nom)  && this.A.get(i).origine.nom.equals(noeud2.nom))) {
				find = true;
			}
		}
		return find;
	}
}
