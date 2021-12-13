package application;

import java.util.LinkedList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Passenger> dispatchContainer;
    private List<Passenger> arrivalContainer;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.dispatchContainer = new LinkedList<>();
        this.arrivalContainer = new LinkedList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Passenger> getDispatchContainer() {
        return dispatchContainer;
    }

    public List<Passenger> getArrivalContainer() {
        return arrivalContainer;
    }

    public void addToArrivalContainer(Passenger passenger) {
        arrivalContainer.add(passenger);
    }

    public void removeFromDispatchContainer(Passenger passenger) {
        dispatchContainer.remove(passenger);
    }
}
