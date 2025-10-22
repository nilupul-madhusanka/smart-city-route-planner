package data;

public class Road {
    private Location from;
    private Location to;
    private int distance;

    public Road(Location from, Location to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return from + " ↔ " + to + " (" + distance + " km)";
    }
}
