package application.handlers;

import application.ArrayOperations;

public class GCDHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        if (arrayOperations.getGcdOfNumbers() != -1) {
            return "Наибольший общий делитель: " + arrayOperations.getGcdOfNumbers();
        } else {
            return "Массив содержит менее двух положительных чисел, посчитать НОД не удалось!";
        }
    }
}
