package application;

import java.util.logging.Logger;

public class ElevatorMovementTask implements Runnable {
    private Elevator elevator;
    private Controller controller;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public ElevatorMovementTask(Elevator elevator, Controller controller) {
        this.elevator = elevator;
        this.controller = controller;
    }

    @Override
    public void run() {
        LOGGER.info("STARTING_TRANSPORTATION");
        while (!controller.сanFinish()) {
            controller.waitForPassengersToDeboard();
            controller.waitForPassengersToBoard();
            controller.moveElevator();
        }
        LOGGER.info("COMPLETION_TRANSPORTATION");
    }
}


