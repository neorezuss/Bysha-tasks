package application;

public class PassengerTransportationTask implements Runnable {
    private Passenger passenger;
    private Controller controller;

    public PassengerTransportationTask(Passenger passenger, Controller controller) {
        this.passenger = passenger;
        this.controller = controller;
        passenger.setTransportationState(TransportationState.IN_PROGRESS);
    }

    @Override
    public void run() {
        controller.boardPassenger(passenger);
        controller.deboardPassenger(passenger);
        passenger.setTransportationState(TransportationState.COMPLETED);
    }
}
