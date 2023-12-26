import dao.RoleDAO;
import dao.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "Amy", "loum");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RoleDAO roleDAO = new RoleDAO(connexion);

        while (continuer) {
            System.out.println("===== Menu =====");
            System.out.println("1. Ajouter");
            System.out.println("2. Lister");
            System.out.println("3. Afficher les rôles");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer

            switch (choix) {
                case 1:
                    System.out.println("Option Ajouter sélectionnée.");

                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();

                    System.out.print("Email : ");
                    String email = scanner.nextLine();

                    System.out.print("Mot de passe : ");
                    System.out.print("Mot de passe : ");
                    String motDePasse = scanner.nextLine();

                    System.out.println("Votre Rôle : ");
                    System.out.println("1. Admin");
                    System.out.println("2. User");
                    System.out.println("3. Developpeur");

                    String roleId = scanner.nextLine();1

                    char[] motDePasseArray = new char[0];
                    Utilisateur nouvelUtilisateur = new Utilisateur(nom, email, new String(motDePasseArray), roleId);
                    roleDAO.ajouterUtilisateur(nouvelUtilisateur, motDePasseArray);
                    System.out.println("Utilisateur ajouté avec succès !");
                    break;
                case 2:
                    System.out.println("Option Lister sélectionnée.");
                    List<Utilisateur> utilisateurs = roleDAO.listerUtilisateurs();
                    for (Utilisateur utilisateur : utilisateurs) {
                        System.out.println("Nom : " + utilisateur.getNom() + ", Email : " + utilisateur.getEmail());
                    }
                    break;
                case 3:
                    System.out.println("Option Afficher les rôles sélectionnée.");
                    utilisateurs = roleDAO.listerUtilisateurs();
                    for (Utilisateur utilisateur : utilisateurs) {
                        System.out.println("Rôle : " + utilisateur.getRole_id());
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
        scanner.close();

        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
