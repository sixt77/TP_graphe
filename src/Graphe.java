
import java.util.*;

public class Graphe {
	public ArrayList<Sommet> G;
	public ArrayList<Arc> A;
	public Graphe() {
		this.G = new ArrayList();
		this.A = new ArrayList();
	}
	public void ajouterSommet(Sommet sommet) {
		if(chercherSommet(sommet.nom) == -1) {
			this.G.add(sommet);
		}else {
			System.out.println("ce noeud existe d�ja");
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
				System.out.println(this.G.get(i).nom);
			}
		}else {
			System.out.println("liste vide");
		}
	}
	public void afficherListeArc() {
		if(this.A.size() > 0) {
			System.out.println("liste des Arc : ");
			for(int i=0; i<this.A.size(); i++) {
				System.out.println("origine : "+this.A.get(i).origine.nom+" -> "+this.A.get(i).fin.nom);
			}
		}else {
			System.out.println("liste vide");
		}
	}
	public Sommet selectionnerSommet() {
		Boolean trouve1 = false;
		Scanner sc = new Scanner(System.in);
		Sommet sommet1 = null;
		while(trouve1 == false) {
			this.afficherListeSommet();
			System.out.println("Veuillez choisir un sommet :");
			String nom1 = sc.nextLine();
			if(this.chercherSommet(nom1) != -1) {
				sommet1 = this.G.get(this.chercherSommet(nom1));
				trouve1 = true;
			}else {
				System.out.println("le sommet n'existe pas dans le graphe");
			}
		}
		return sommet1;
	}
	public int chercherChemin(Sommet sommet1, Sommet sommet2) {
		return 0;

	}
	public ArrayList<Sommet> chercherVoisin(Sommet sommet1){
		ArrayList<Sommet> listeVoisin = null;
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.nom.equals(sommet1.nom)) {
				listeVoisin.add(this.A.get(i).origine);
			}
			if(this.A.get(i).fin.nom.equals(sommet1.nom)) {
				listeVoisin.add(this.A.get(i).fin);
			}
		}
		return listeVoisin;
	}

	
	public Boolean propagation(Sommet sommet1, Sommet sommet2){
		int i = 1;
		int j = 0;
				
		ArrayList<Sommet> liste1 = new ArrayList<Sommet>();
		liste1.add(sommet1);

		while(i != j) {
			i = liste1.size();
			for(int x=0; x<liste1.size(); x++) {
				liste1 = this.chercherVoisin(liste1.get(x), liste1);
			}
			j = liste1.size();
		}
		if(liste1.indexOf(sommet2) != -1) {
			return true;
		}else {
			return false;
		}

	}
	
	public ArrayList<Sommet> chercherVoisin(Sommet sommet1, ArrayList<Sommet> liste1){
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.nom.equals(sommet1.nom)) {
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

	public int lemmeDeKoening(Sommet sommet1, Sommet sommet2) {
		ArrayList<Sommet> listSommet = null;
		ArrayList<Sommet> listNouveauSommet = null;
		listSommet.add(sommet1);
		listNouveauSommet.add(sommet1);
		while(listSommet.indexOf(sommet2) == -1) {
			for(int i=0; i<listNouveauSommet.size(); i++) {
				listNouveauSommet = this.chercherVoisin(listNouveauSommet.get(i), listSommet);
			}

		}
		return 0;
	}
	public boolean cheminEntre2Sommet(Sommet sommet1, Sommet sommet2) {
		boolean find = false;
		for(int i=0; i<this.A.size(); i++) {
			if((this.A.get(i).origine.nom.equals(sommet1.nom)  && this.A.get(i).fin.nom.equals(sommet2.nom))||(this.A.get(i).fin.nom.equals(sommet1.nom)  && this.A.get(i).origine.nom.equals(sommet2.nom))) {
				find = true;
			}
		}
		return find;
	}
}
