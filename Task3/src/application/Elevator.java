package application;

import java.util.LinkedList;
import java.util.List;

public class Elevator {
    private int topFloor;
    private int capacity;
    private List<Passenger> container;
    private int currentFloor;
    private MovementDirection movementDirection;

    public Elevator(int topFloor, int capacity) {
        this.topFloor = topFloor;
        this.capacity = capacity;
        this.container = new LinkedList();
        this.currentFloor = 1;
        this.movementDirection = MovementDirection.UP;
    }

    public void addPassenger(Passenger passenger) {
        container.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        container.remove(passenger);
    }

    public boolean containsPassenger(Passenger passenger) {
        return container.contains(passenger);
    }

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(MovementDirection movementDirection) {
        this.movementDirection = movementDirection;
    }

    public int getTopFloor() {
        return topFloor;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getContainer() {
        return container;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
