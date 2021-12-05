package application.handlers;

import application.ArrayOperations;

public class DecimalPeriodHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        if (arrayOperations.getDecimalPeriod() != -1) {
            return "Период десятичной дроби: " + arrayOperations.getDecimalPeriod();
        } else {
            return "Период дроби посчитать не удалось! Массив не содержит двух положительных чисел, расположенных подряд!";
        }
    }
}
