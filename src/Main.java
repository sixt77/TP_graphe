
import java.util.*;


public class Main {

	public static void main(String[] args) {
		Boolean fin = false;
		Graphe graphe = new Graphe();

		while(!fin) {
			System.out.println("1- creer noeud");
			System.out.println("2- creer arc");
			System.out.println("3- afficher liste sommet");
			System.out.println("4- afficher liste arc");
			System.out.println("5- existe chemin entre 2 sommet");
			System.out.println("7- finir");
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez saisir votre choix :");
			int value = sc.nextInt();
			switch (value)

			{
			case 1:
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Veuillez saisir un nom :");
				String nom = sc2.nextLine();
				graphe.ajouterSommet(new Sommet(nom));
				break;

			case 2:
				Sommet sommet1 = graphe.selectionnerSommet();
				Sommet sommet2 = graphe.selectionnerSommet();
				graphe.A.add(new Arc(sommet1, sommet2));
				System.out.println("arc ajout�");
				
				break;  
				
			case 3:
				graphe.afficherListeSommet();
				break;
				
			case 4:
				graphe.afficherListeArc();
				break;
				
			case 5:
				Sommet sommet3 = graphe.selectionnerSommet();
				Sommet sommet4 = graphe.selectionnerSommet();
				if(graphe.propagation(sommet3, sommet4)) {
					System.out.println("arc trouv�");
				}else {
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
