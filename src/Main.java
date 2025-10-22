import data.*;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        LocationTree tree = new LocationTree();
        Menu menu = new Menu(graph, tree);

        menu.showMenu();
    }
}
