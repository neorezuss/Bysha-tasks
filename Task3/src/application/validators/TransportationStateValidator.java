package application.validators;

import application.Application;
import application.Floor;
import application.TransportationState;

import java.util.List;
import java.util.logging.Logger;

public class TransportationStateValidator implements Validator {
    private List<Floor> floors;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public TransportationStateValidator(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public void validate() {
        boolean isValid = floors.stream()
                .flatMap(floor -> floor.getArrivalContainer().stream())
                .allMatch(passenger -> passenger.getTransportationState() == TransportationState.COMPLETED);
        LOGGER.info("All passengers have completed transportation state - " + isValid);
    }
}
