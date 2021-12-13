package application.validators;

import application.Application;
import application.Floor;

import java.util.List;
import java.util.logging.Logger;

public class PassengersNumberValidator implements Validator {
    private List<Floor> floors;
    private int passengersNumber;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public PassengersNumberValidator(List<Floor> floors, int passengersNumber) {
        this.floors = floors;
        this.passengersNumber = passengersNumber;
    }

    @Override
    public void validate() {
        boolean isValid = floors.stream()
                .flatMap(floor -> floor.getArrivalContainer().stream())
                .count() == passengersNumber;
        LOGGER.info("Number of passengers in arrival containers is equals to initial number of passengers - " + isValid);
    }
}
