package ui;

import data.*;
import java.util.Scanner;

public class Menu {
    private final Graph graph;
    private final LocationTree tree;
    private final Scanner sc;

    public Menu(Graph graph, LocationTree tree) {
        this.graph = graph;
        this.tree = tree;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations");
            System.out.println("7. BFS Traversal");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String loc = sc.nextLine();
                    graph.addLocation(loc);
                    tree.insert(loc);
                    break;
                case 2:
                    System.out.print("Enter location name to remove: ");
                    String rem = sc.nextLine();
                    graph.removeLocation(rem);
                    break;
                case 3:
                    System.out.print("Enter starting location: ");
                    String from = sc.nextLine();
                    System.out.print("Enter destination location: ");
                    String to = sc.nextLine();
                    graph.addRoad(from, to);
                    break;
                case 4:
                    System.out.print("Enter starting location: ");
                    from = sc.nextLine();
                    System.out.print("Enter destination location: ");
                    to = sc.nextLine();
                    graph.removeRoad(from, to);
                    break;
                case 5:
                    graph.displayConnections();
                    break;
                case 6:
                    tree.displayInOrder();
                    break;
                case 7:
                    System.out.print("Enter start location: ");
                    String start = sc.nextLine();
                    graph.bfsTraversal(start);
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 8);
    }
}
