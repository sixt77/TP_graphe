
import java.util.*;

public class Graphe {
	public ArrayList<Sommet> G;
	public ArrayList<Arc> A;
	public Graphe() {
		this.G = new ArrayList();
		this.A = new ArrayList();
	}
	public void ajouterSommet(Sommet sommet) {
		if(!sommet.type.equals("attribut")){
			if(chercherSommet(sommet.valeur) == -1) {
				this.G.add(sommet);
			}else {
				System.out.println("ce noeud existe déja");
			}
		}else{
			this.G.add(sommet);
		}
		System.out.println(this.G.size());

	}
	public void ajouterArc(Arc arc) {
		this.A.add(arc);
	}
	public int chercherSommet(String valeur) {
		boolean find = false;
		int rank = 0;
		for(int i=0; i<this.G.size(); i++) {
			if(this.G.get(i).valeur.equals(valeur)) {
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
				if(this.G.get(i).type.equals("noeud")){
					System.out.println(this.G.get(i).valeur);
				}
			}
		}else {
			System.out.println("liste vide");
		}
	}
	public void afficherListeArc() {
		if(this.A.size() > 0) {
			System.out.println("liste des Arc : ");
			for(int i=0; i<this.A.size(); i++) {
				if(this.A.get(i).type.equals("is_a")){
					System.out.println(this.A.get(i).origine.valeur+" (hérite de)-> "+this.A.get(i).fin.valeur);
				}
				if(this.A.get(i).type.equals("friend")){
					System.out.println(this.A.get(i).origine.valeur+" (est amis de )-> "+this.A.get(i).fin.valeur);
				}
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
			String valeur1 = sc.nextLine();
			if(this.chercherSommet(valeur1) != -1) {
				sommet1 = this.G.get(this.chercherSommet(valeur1));
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
	public ArrayList<Arc> chercherAttribut(Sommet sommet1){
		ArrayList<Arc> listeAttribut = new ArrayList<Arc>();
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.valeur.equals(sommet1.valeur) && this.A.get(i).type.equals("attribut")) {
				listeAttribut.add(this.A.get(i));
			}
		}
		return listeAttribut;
	}

	public ArrayList<Arc> chercherAttribut(ArrayList<Sommet> liste1){
		ArrayList<Arc> listeAttribut = new ArrayList<Arc>();
		for(int i=0; i<liste1.size(); i++) {
			for(int j=0; j<this.A.size(); j++) {
				if(this.A.get(i).origine.valeur.equals(liste1.get(i).valeur) && this.A.get(i).type.equals("attribut")) {
					listeAttribut.add(this.A.get(i));
				}
			}
		}
		return listeAttribut;
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

	public boolean attributExists(Sommet sommet1, String attribut){
		ArrayList<Arc> listeAttribut = new ArrayList<Arc>();
		listeAttribut = chercherAttribut(sommet1);
		Boolean find = false;
		for(int i=0; i<listeAttribut.size(); i++) {
			if(listeAttribut.get(i).valeur.equals(attribut) && listeAttribut.get(i).type.equals("attribut")){
				find = true;
			}
		}
		return find;
	}
	public void afficherListeAttribut(Sommet sommet1){
		ArrayList<Arc> listeAttribut = new ArrayList<Arc>();
		listeAttribut = chercherAttribut(sommet1);
		if(listeAttribut.size() == 0){
			System.out.println("aucun attribut trouvé");
		}else{
			for(int i=0; i<listeAttribut.size(); i++) {
				System.out.println("attribut : "+listeAttribut.get(i).valeur+" valeur : "+listeAttribut.get(i).fin.valeur);
			}
		}
	}
	public ArrayList<Sommet> chercherVoisin(Sommet sommet1, ArrayList<Sommet> liste1){
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).origine.valeur.equals(sommet1.valeur)) {
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
				if(this.A.get(i).origine.valeur.equals(liste1.get(i))) {
					if(liste1.indexOf(this.A.get(j).origine)==-1) {
						listeVoisin.add(this.A.get(j).origine);
					}
				}
				if(this.A.get(i).fin.valeur.equals(liste1.get(i))) {
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
			if((this.A.get(i).origine.valeur.equals(sommet1.valeur)  && this.A.get(i).fin.valeur.equals(sommet2.valeur))||(this.A.get(i).fin.valeur.equals(sommet1.valeur)  && this.A.get(i).origine.valeur.equals(sommet2.valeur))) {
				find = true;
			}
		}
		return find;
	}

	public boolean parcoursLargeur(Sommet sommet1, Sommet sommet2){
		ArrayList<Sommet> liste1 = new ArrayList<Sommet>();
		ArrayList<Sommet> liste2 = new ArrayList<Sommet>();
		ArrayList<Sommet> tmp = new ArrayList<Sommet>();
		int j = liste1.size();
		liste1.add(sommet1);
		int i = liste1.size();
		this.printList(liste1);
		boolean find = false;
		int loop = 0;
		while(i != j && !find){
			System.out.println("loop");
			j = liste2.size();
			//met liste 1 dans tmp
			tmp.clear();
			for(int y = 0; y <liste1.size(); y++){
				tmp.add(liste1.get(y));
				System.out.println("add");
			}
			//liste1 clear
			liste1.clear();
			for(int x=0; x<tmp.size(); x++) {
				System.out.println("x : "+x);
				//chercher les sommet voisin et add dans liste 1 si il n'est pas dans la liste 2
				for(int z = 0; z<this.A.size(); z++){
					System.out.println("z : "+z);
					if(this.A.get(z).origine.valeur.equals(tmp.get(x).valeur)&& !find){
						System.out.println("fin : "+this.A.get(z).fin.valeur);
						if(liste2.indexOf(this.A.get(z).fin) ==-1 && liste1.indexOf(this.A.get(z).fin) ==-1){
							liste1.add(this.A.get(z).fin);
							if(this.A.get(z).fin.valeur.equals(sommet2.valeur)){
								find = true;
							}
						}
					}
				}
				liste2.add(tmp.get(x));

			}
			this.printList(liste1);
			this.printList(liste2);
			i = liste2.size();
			System.out.println("i : "+i);
			System.out.println("j : "+j);
		}
		if(liste2.indexOf(sommet2) != -1){
			return true;
		}else{
			return false;
		}
	}




	public boolean checkLoop(Sommet sommet1){
		ArrayList<Sommet> liste1 = new ArrayList<Sommet>();
		ArrayList<Sommet> liste2 = new ArrayList<Sommet>();
		ArrayList<Sommet> tmp = new ArrayList<Sommet>();
		int j = liste1.size();
		liste1.add(sommet1);
		int i = liste1.size();
		this.printList(liste1);
		boolean find = false;
		int loop = 0;
		while(i != j && !find){
			System.out.println("loop");
			j = liste2.size();
			//met liste 1 dans tmp
			tmp.clear();
			for(int y = 0; y <liste1.size(); y++){
				tmp.add(liste1.get(y));
				System.out.println("add");
			}
			//liste1 clear
			liste1.clear();
			for(int x=0; x<tmp.size(); x++) {
				System.out.println("x : "+x);
				//chercher les sommet voisin et add dans liste 1 si il n'est pas dans la liste 2
				for(int z = 0; z<this.A.size(); z++){
					System.out.println("z : "+z);
					if(this.A.get(z).origine.valeur.equals(tmp.get(x).valeur)&& !find &&(this.A.get(z).type.equals("is_a"))){
						System.out.println("fin : "+this.A.get(z).fin.valeur);
						liste1.add(this.A.get(z).fin);
						if(this.A.get(z).fin.valeur.equals(sommet1.valeur)){
							find = true;
						}
					}
				}
				liste2.add(tmp.get(x));

			}
			this.printList(liste1);
			this.printList(liste2);
			i = liste2.size();
			System.out.println("i : "+i);
			System.out.println("j : "+j);
		}
		return find;
	}

	public ArrayList<Sommet> findAllParent(Sommet sommet1){
		ArrayList<Sommet> liste1 = new ArrayList<Sommet>();
		ArrayList<Sommet> liste2 = new ArrayList<Sommet>();
		ArrayList<Sommet> tmp = new ArrayList<Sommet>();
		int j = liste1.size();
		liste1.add(sommet1);
		int i = liste1.size();
		this.printList(liste1);
		int loop = 0;
		while(i != j){
			System.out.println("loop");
			j = liste2.size();
			//met liste 1 dans tmp
			tmp.clear();
			for(int y = 0; y <liste1.size(); y++){
				tmp.add(liste1.get(y));
				System.out.println("add");
			}
			//liste1 clear
			liste1.clear();
			for(int x=0; x<tmp.size(); x++) {
				System.out.println("x : "+x);
				//chercher les sommet voisin et add dans liste 1 si il n'est pas dans la liste 2
				for(int z = 0; z<this.A.size(); z++){
					System.out.println("z : "+z);
					if(this.A.get(z).origine.valeur.equals(tmp.get(x).valeur) && (this.A.get(z).type.equals("is_a"))){
						System.out.println("fin : "+this.A.get(z).fin.valeur);
						if(liste2.indexOf(this.A.get(z).fin) ==-1 && liste1.indexOf(this.A.get(z).fin) ==-1){
							liste1.add(this.A.get(z).fin);
						}
					}
				}
				liste2.add(tmp.get(x));

			}
			this.printList(liste1);
			this.printList(liste2);
			i = liste2.size();
			System.out.println("i : "+i);
			System.out.println("j : "+j);
			for(int x=0; x<liste2.size(); x++) {
				System.out.println("noeud : "+liste2.get(x).valeur);
			}
		}
		return liste2;
	}

	public void displayAllAttribut(ArrayList<Sommet> liste1){
		for(int i=0; i<liste1.size(); i++) {
			displayAllAttribut(liste1.get(i));
		}

	}
	public void displayAllAttribut(Sommet sommet1){
		for(int i=0; i<this.A.size(); i++) {
			if(this.A.get(i).type.equals("attribut") && this.A.get(i).origine.valeur.equals(sommet1.valeur)){
				System.out.println("attribut : "+this.A.get(i).valeur+" valeur : "+this.A.get(i).fin.valeur);
			}
		}
	}

	public ArrayList<Sommet> diff(ArrayList<Sommet> liste1, ArrayList<Sommet> liste2){
		ArrayList<Sommet> diff = new ArrayList<>();
		for(int i=0; i<liste1.size(); i++) {
			if(liste2.indexOf(liste1.get(i)) == -1){
				diff.add(liste1.get(i));
			}
		}
		for(int i=0; i<liste2.size(); i++) {
			if(liste1.indexOf(liste2.get(i)) == -1){
				diff.add(liste2.get(i));
			}
		}
		return diff;
	}
	public ArrayList<Sommet> rechercheSommetSuivant(ArrayList<Sommet> liste1){
		ArrayList<Sommet> tmp = new ArrayList<Sommet>();
		for(int i = 0; i <liste1.size(); i++){
			for(int j=0; j<this.A.size(); j++) {
				if(this.A.get(j).origine.valeur.equals(liste1.get(i))) {
					if(tmp.indexOf(this.A.get(j).fin)==-1) {
						tmp.add(this.A.get(j).fin);
					}
				}
			}
		}
		return tmp;
	}
	public void printList(ArrayList<Sommet> liste1){
		for(int i = 0; i <liste1.size(); i++){
			System.out.println("sommet "+i+" :"+liste1.get(i).valeur);
		}
	}


}
