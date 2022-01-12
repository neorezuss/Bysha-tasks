package application.validators;

import application.Application;
import application.Elevator;

import java.util.logging.Logger;

public class ElevatorContainerValidator implements Validator {
    private Elevator elevator;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public ElevatorContainerValidator(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void validate() {
        boolean isValid = elevator.getContainer().isEmpty();
        LOGGER.info("Elevator container is empty - " + isValid);
    }
}
