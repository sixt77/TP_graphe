
import java.util.*;


public class Main {

	public static void main(String[] args) {
		Boolean fin = false;
		Graphe graphe = new Graphe();

		while (!fin) {
			System.out.println(" Commandes :");
			System.out.println("1- creer noeud");
			System.out.println("2- creer arc");
			System.out.println("3- ajouter attribut");
			System.out.println("4- afficher liste sommet");
			System.out.println("5- afficher liste arc");
			System.out.println("6- afficher attribut d'un sommet");
			System.out.println("7- liste des attributs");
			System.out.println("8- finir");
			System.out.println("9- supprimer noeud");
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez saisir votre choix :");
			int value = sc.nextInt();
			switch (value) {
				case 1:
					Scanner sc2 = new Scanner(System.in);
					Sommet nouveau;
					System.out.println("Veuillez saisir un nom :");
					String nom = sc2.nextLine();
					graphe.ajouterSommet(nouveau = new Sommet(nom, "noeud"));
					break;

				case 2:
					Arc nouveauArc;
					Sommet noeud1 = graphe.selectionnerSommet();
					Sommet noeud2 = graphe.selectionnerSommet();
					Scanner sc3 = new Scanner(System.in);
					System.out.println("Veuillez saisir un type, 1 pour héritage, 2 pour amitié :");
					int number = sc3.nextInt();
					if(number == 1){
						graphe.A.add(nouveauArc = new Arc(noeud1, noeud2, "is_a"));
						if(graphe.checkLoop(noeud1) ||graphe.checkLoop(noeud2)){
							System.out.println("une boucle a été détécter, vous ne pouvez pas ajouter cet arc");
							graphe.A.remove(nouveauArc);
						}else{
							System.out.println("arc ajouté");
						}
					}
					if(number == 2){
						graphe.A.add(nouveauArc = new Arc(noeud1, noeud2, "friend"));
					}




					break;

				case 3:
					Sommet S1 = graphe.selectionnerSommet();
					Scanner sc4 = new Scanner(System.in);
					System.out.println("Veuillez saisir le type de d'attribut");
					String type = sc4.nextLine();
					if(graphe.attributExists(S1, type)){
						System.out.println("l'attribut existe déja");

					}else{
						Scanner sc5 = new Scanner(System.in);
						System.out.println("Veuillez saisir la valeur de d'attribut");
						String valeur = sc5.nextLine();
						graphe.ajouterSommet(nouveau = new Sommet(valeur, "attribut"));
						graphe.A.add(nouveauArc = new Arc(S1, nouveau, "attribut", type));
					}

					break;
				case 4:
					graphe.afficherListeSommet();
					break;

				case 5:
					graphe.afficherListeArc();
					break;

				case 6:
					Sommet noeud3 = graphe.selectionnerSommet();
					graphe.afficherListeAttribut(noeud3);
					break;

				case 7:
					Sommet sommet5 = graphe.selectionnerSommet();
					graphe.displayAllAttribut(graphe.findAllParent(sommet5));

					break;
				case 8:
					fin = true;
					System.out.println("aurevoir");
					break;
				case 9:
					Sommet sommet6 = graphe.selectionnerSommet();
					graphe.effaceSommet(sommet6);
					break;

				default:
					System.out.println("erreur");
			}

		}

	}

}
