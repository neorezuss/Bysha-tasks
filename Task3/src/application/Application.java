package application;

import application.validators.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Application {
    private int floorsNumber;
    private int elevatorCapacity;
    private int passengersNumber;
    private static Logger LOGGER;

    static {
        try (FileInputStream ins = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Application.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        readProperties();
        Elevator elevator = new Elevator(floorsNumber, elevatorCapacity);
        Building building = new Building(elevator);
        for (int i = 1; i <= floorsNumber; i++) {
            Floor floor = new Floor(i);
            building.getFloors().add(floor);
        }
        Controller controller = new Controller(building.getFloors(), elevator);
        ExecutorService executorService = Executors.newFixedThreadPool(passengersNumber + 1);
        for (int i = 1; i <= passengersNumber; i++) {
            Passenger passenger = new Passenger(i, floorsNumber);
            building.getFloors().get(passenger.getSourceFloor() - 1).getDispatchContainer().add(passenger);
            executorService.execute(new PassengerTransportationTask(passenger, controller));
        }
        executorService.execute(new ElevatorMovementTask(elevator, controller));
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        List<Validator> validators = List.of(
                new DispatchContainersValidator(building.getFloors()),
                new ElevatorContainerValidator(elevator),
                new TransportationStateValidator(building.getFloors()),
                new PassengersNumberValidator(building.getFloors(), passengersNumber)
        );
        validators.forEach(Validator::validate);
    }

    public void readProperties() {
        FileInputStream fileInputStream;
        Properties property = new Properties();
        try {
            fileInputStream = new FileInputStream("config.properties");
            property.load(fileInputStream);
            floorsNumber = Integer.parseInt(property.getProperty("floorsNumber"));
            elevatorCapacity = Integer.parseInt(property.getProperty("elevatorCapacity"));
            passengersNumber = Integer.parseInt(property.getProperty("passengersNumber"));
        } catch (IOException e) {
            System.err.println("Файл свойств отсуствует!");
        }
    }
}
