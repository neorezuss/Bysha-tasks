package application;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private List<Floor> floors;
    private Elevator elevator;
    private ReentrantLock reentrantLock;
    private Map<Integer, Condition> floorConditions;
    private static Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(Application.class.getName());
    }

    public Controller(List<Floor> floors, Elevator elevator) {
        this.elevator = elevator;
        this.floors = floors;
        this.reentrantLock = new ReentrantLock();
        this.floorConditions = Stream.iterate(0, n -> n + 1)
                .limit(floors.size())
                .collect(Collectors.toMap(i -> i, (i) -> reentrantLock.newCondition()));
    }

    public void moveElevator() {
        reentrantLock.lock();
        if (elevator.getCurrentFloor() == 1) {
            elevator.setMovementDirection(MovementDirection.UP);
        } else if (elevator.getCurrentFloor() == elevator.getTopFloor()) {
            elevator.setMovementDirection(MovementDirection.DOWN);
        }
        int nextFloor;
        if (elevator.getMovementDirection() == MovementDirection.UP) {
            nextFloor = elevator.getCurrentFloor() + 1;
        } else {
            nextFloor = elevator.getCurrentFloor() - 1;
        }
        LOGGER.info("MOVING_ELEVATOR from floor " + elevator.getCurrentFloor() + " to floor " + nextFloor);
        floorConditions.get(elevator.getCurrentFloor() - 1).signalAll();
        elevator.setCurrentFloor(nextFloor);
        reentrantLock.unlock();
    }

    public void waitForPassengersToDeboard() {
        reentrantLock.lock();
        while (!canDeboardSomeone()) {
            await();
        }
        floorConditions.get(elevator.getCurrentFloor() - 1).signalAll();
        reentrantLock.unlock();
    }

    public void waitForPassengersToBoard() {
        reentrantLock.lock();
        while (!canBoardSomeone()) {
            await();
        }
        floorConditions.get(elevator.getCurrentFloor() - 1).signalAll();
        reentrantLock.unlock();
    }

    private boolean canBoardSomeone() {
        return floors.get(elevator.getCurrentFloor() - 1).getDispatchContainer().isEmpty()
                || !(elevator.getCapacity() - elevator.getContainer().size() > 0);
    }

    private boolean canDeboardSomeone() {
        return elevator.getContainer().stream()
                .noneMatch(passenger -> passenger.getDestinationFloor() == elevator.getCurrentFloor());
    }


    public void boardPassenger(Passenger passenger) {
        reentrantLock.lock();
        while (!canBoard(passenger)) {
            await();
        }
        Floor floor = floors.get(elevator.getCurrentFloor() - 1);
        LOGGER.info("BOARDING_OF_PASSENGER passenger-" + passenger.getId() + " on floor " + floors.get(elevator.getCurrentFloor() - 1).getFloorNumber());
        floor.removeFromDispatchContainer(passenger);
        elevator.addPassenger(passenger);
        floorConditions.get(elevator.getCurrentFloor() - 1).signalAll();
        reentrantLock.unlock();
    }

    private boolean canBoard(Passenger passenger) {
        return elevator.getCurrentFloor() == passenger.getSourceFloor()
                && elevator.getCapacity() - elevator.getContainer().size() > 0;
    }

    public void deboardPassenger(Passenger passenger) {
        reentrantLock.lock();
        while (!canDeboard(passenger)) {
            await();
        }
        Floor floor = floors.get(elevator.getCurrentFloor() - 1);
        LOGGER.info("DEBOARDING_OF_PASSENGER passenger-" + passenger.getId() + " on floor " + floors.get(elevator.getCurrentFloor() - 1).getFloorNumber());
        elevator.removePassenger(passenger);
        floor.addToArrivalContainer(passenger);
        floorConditions.get(elevator.getCurrentFloor() - 1).signalAll();
        reentrantLock.unlock();
    }

    private boolean canDeboard(Passenger passenger) {
        return elevator.getCurrentFloor() == passenger.getDestinationFloor()
                && elevator.containsPassenger(passenger);
    }

    public boolean сanFinish() {
        reentrantLock.lock();
        boolean isElevatorEmpty = elevator.getContainer().isEmpty();
        boolean areDispatchContainersEmpty = floors.stream()
                .allMatch(floor -> floor.getDispatchContainer().isEmpty());
        reentrantLock.unlock();
        return isElevatorEmpty && areDispatchContainersEmpty;
    }

    private void await() {
        try {
            floorConditions.get(elevator.getCurrentFloor() - 1).await();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}


