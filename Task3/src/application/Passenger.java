package application;

public class Passenger {
    private int id;
    private int sourceFloor;
    private int destinationFloor;
    private TransportationState transportationState;

    public Passenger(int id, int floorsNumber) {
        this.id = id;
        this.sourceFloor = (int) (floorsNumber * Math.random() + 1);
        while (true) {
            int destinationFloor = (int) (floorsNumber * Math.random() + 1);
            if (sourceFloor != destinationFloor) {
                this.destinationFloor = destinationFloor;
                break;
            }
        }
        this.transportationState = TransportationState.NOT_STARTED;
    }

    public int getId() {
        return id;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setTransportationState(TransportationState transportationState) {
        this.transportationState = transportationState;
    }

    public TransportationState getTransportationState() {
        return transportationState;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", sourceFloor=" + sourceFloor +
                ", destinationFloor=" + destinationFloor +
                ", transportationState=" + transportationState +
                '}';
    }
}
