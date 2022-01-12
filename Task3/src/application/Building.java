package application;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors;
    private Elevator elevator;

    public Building(Elevator elevator) {
        this.floors = new ArrayList<>();
        this.elevator = elevator;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
