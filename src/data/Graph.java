package data;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add new location (node)
    public void addLocation(String location) {
        if (!adjacencyList.containsKey(location)) {
            adjacencyList.put(location, new ArrayList<>());
            System.out.println("Location added: " + location);
        } else {
            System.out.println("Location already exists!");
        }
    }

    // Remove a location
    public boolean removeLocation(String location) {
        String loc = safeName(location);
        if (loc.isEmpty()) {
            System.out.println("Invalid location name.");
            return false;
        }
        if (adjacencyList.containsKey(loc)) {
            adjacencyList.remove(loc);
            for (List<String> list : adjacencyList.values()) {
                list.remove(loc);
            }
            System.out.println("Location removed: " + loc);
            return true;
        } else {
            System.out.println("Location not found!");
            return false;
        }
    }

    // Add a road (edge)
    public void addRoad(String from, String to) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from); // undirected graph
            System.out.println("Road added between " + from + " and " + to);
        } else {
            System.out.println("One or both locations not found!");
        }
    }

    // Remove a road (edge)
    public void removeRoad(String from, String to) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).remove(to);
            adjacencyList.get(to).remove(from);
            System.out.println("Road removed between " + from + " and " + to);
        } else {
            System.out.println("One or both locations not found!");
        }
    }

    // Display all connections
    public void displayConnections() {
        System.out.println("\n--- City Connections ---");
        if (adjacencyList.isEmpty()) {
            System.out.println("No locations added yet.");
            return;
        }
        // Sort locations for stable display order
        List<String> locations = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(locations);
        for (String location : locations) {
            List<String> neighbors = new ArrayList<>(adjacencyList.get(location));
            Collections.sort(neighbors);
            System.out.println(location + " -> " + neighbors);
        }
    }

    // Example traversal using Queue (BFS)
    public void bfsTraversal(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Location not found!");
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.println("\nBFS Traversal from " + start + ":");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    private String safeName(String name) {
        return name == null ? "" : name.trim();
    }
}
