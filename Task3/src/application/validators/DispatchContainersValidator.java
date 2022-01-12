package application.validators;

import application.Application;
import application.Floor;

import java.util.List;
import java.util.logging.Logger;

public class DispatchContainersValidator implements Validator {
    private List<Floor> floors;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public DispatchContainersValidator(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public void validate() {
        boolean isValid = floors.stream().allMatch(floor -> floor.getDispatchContainer().isEmpty());
        LOGGER.info("All dispatch containers are empty - " + isValid);
    }
}
