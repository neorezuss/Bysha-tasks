package application.handlers;

import application.ArrayOperations;

public class LCMHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        if (arrayOperations.getLcmOfNumbers() != -1) {
            return "Наименьшее общее кратное: " + arrayOperations.getLcmOfNumbers();
        } else {
            return "Массив содержит менее двух положительных чисел, посчитать НОК не удалось!";
        }
    }
}
