# Smart City Route Planner

A simple console-based application to manage city locations and roads, and to explore connectivity using Breadth-First Search (BFS). The project demonstrates core data structures (undirected graph and AVL tree) with robust, user-friendly input validation and clear error handling.

### Highlights
- Undirected graph with adjacency lists to model roads between locations
- AVL tree to display locations in a sorted, balanced structure (in-order)
- BFS traversal from a chosen start location
- Safe input handling (numeric range checks, non-empty strings, confirmations)
- Clear, concise messages and maintainable Javadoc/comments

---

## Project structure

```
src/
  Main.java                 # Entry point; wires Graph + LocationTree + Menu
  data/
	 Graph.java              # Undirected graph using adjacency lists
	 LocationTree.java       # AVL tree for sorted display of locations
	 AVLNode.java            # Internal node for the AVL tree
	 Location.java           # Simple value object for a location name
	 Road.java               # Value object for a road (future weighted graph)
  ui/
	 Menu.java               # Interactive CLI with robust input validation
```

---

## Prerequisites

- Java Development Kit (JDK) 8+ installed
- Windows PowerShell (commands below are tailored for Windows)

Verify Java installation:

```powershell
java -version
javac -version
```

## Installation
```bash
git clone https://github.com/nilupul-madhusanka/smart-city-route-planner

```
## Using the app

Menu options:

1) Add a new location
	- Prompts for a non-empty name; ignores whitespace-only input.
	- Prevents duplicates.

2) Remove a location
	- When deleting it, remove all incident roads.
	- Notifies if the location doesn’t exist.

3) Add a road between locations
	- Connects two existing locations in an undirected manner.
	- Prevents self-loops and duplicate roads; warns if a location is missing.

4) Remove a road
	- Removes the bidirectional connection; warns if the road doesn’t exist.

5) Display all connections
	- Prints each location and its sorted list of neighbors; handles empty graphs gracefully.

6) Display all locations (Tree view)
	- Shows an in-order traversal of the AVL tree for a sorted list; reports when empty.

7) BFS Traversal
	- Runs a breadth-first traversal from the provided start location; warns if missing.

0) Exit

Example flow:

```
1) Add a new location -> "A"
1) Add a new location -> "B"
3) Add a road -> from "A" to "B"
5) Display connections        # A -> [B], B -> [A]
7) BFS Traversal -> start "A" # Output: A B
```

---

## Design notes

- Graph: `Map<String, List<String>>` adjacency list
  - Methods validate names and return booleans to indicate success/failure while printing helpful messages.
  - Prevents duplicates and self-loop edges; provides `hasLocation` and `hasRoad` helpers.

- AVL tree: `LocationTree`
  - Used for sorted display of location names; public `insert` safely ignores blank input.
  - `displayInOrder` handles an empty tree gracefully.

- Menu: `Menu.java`
  - Centralized input helpers: integer range, non-empty strings, and yes/no confirmation.
  - Clear colorized feedback, with graceful fallback on consoles without ANSI.

---

## Error handling and validation

- All user inputs are validated with retry loops.
- Location/road operations check for existence and logical constraints (no self-loops, no duplicates).
- Messages are user-friendly and explain why an operation failed.

---

## Roadmap and extensions

- Delete support in `LocationTree` to mirror graph deletions (currently only inserts).
- Switch adjacency lists to `Set<String>` to naturally prevent duplicates without explicit checks.
- Add persistence (save/load locations and roads to a file).
- Implement weighted graphs using `Road` and shortest-path algorithms (Dijkstra/A*).
- Add JUnit tests for `Graph` and menu helpers.

---

## License

Educational project. If you reuse the code, please attribute the original author.
