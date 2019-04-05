
import java.util.*;


public class Main {

	public static void main(String[] args) {
		Boolean fin = false;
		Boolean choixtype = false;
		Graphe graphe = new Graphe();

		while (!fin) {
			System.out.println(" Commandes :");
			System.out.println("1- creer noeud");
			System.out.println("2- creer arc");
			System.out.println("3- afficher liste sommet");
			System.out.println("4- afficher liste arc");
			System.out.println("5- existe chemin entre 2 sommet");
			System.out.println("7- finir");
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez saisir votre choix :");
			int value = sc.nextInt();
			switch (value) {
				case 1:
					Scanner sc2 = new Scanner(System.in);
					Sommet nouveau;
					System.out.println("Veuillez saisir un nom :");
					String nom = sc2.nextLine();
					System.out.println("Veuillez saisir un type :(instance ou concept 'i' ou 'c') ");
					String type = sc2.nextLine();
					while (!choixtype){
						if(type.equals("i") || type.equals("c")){
							choixtype = true;
						}else{
							System.out.println("VEUILLEZ SAISIR 'i' OU 'c'");
							type = sc2.nextLine();
						}
					}

					System.out.println("Voulez vous ajouter plus d'information sur votre noeud (oui/non) ?");
					String choix = sc2.nextLine();
					graphe.ajouterSommet(nouveau = new Sommet(nom, type));
					if (choix.equals("oui")) {
						System.out.println("Veuillez saisir une adresse ");
						String adresse = sc2.nextLine();
						System.out.println("Veuillez saisir un age ");
						int age = sc2.nextInt();
						nouveau.adresse = adresse;
						nouveau.age = age;
					}
					break;

				case 2:
					Arc nouveauArc;
					Sommet noeud1 = graphe.selectionnerSommet();
					Sommet noeud2 = graphe.selectionnerSommet();
					graphe.A.add(nouveauArc = new Arc(noeud1, noeud2));
					if (noeud1.type.equals("i") && noeud2.type.equals("i")) {
						nouveauArc.est_Ami = true;
					}
					if (noeud1.type.equals("i") && noeud2.type.equals("c")) {
						nouveauArc.is_A = true;
					}
					System.out.println("arc ajouté");

					break;

				case 3:
					graphe.afficherListeSommet();
					break;

				case 4:
					graphe.afficherListeArc();
					break;

				case 5:
					Sommet noeud3 = graphe.selectionnerSommet();
					Sommet noeud4 = graphe.selectionnerSommet();
					if (graphe.propagation(noeud3, noeud4)) {
						System.out.println("arc trouv�");
					} else {
						System.out.println("arc non trouv�");
					}
					break;
				case 7:
					fin = true;
					System.out.println("aurevoir");
					break;

				default:
					System.out.println("erreur");
			}

		}

	}

}
