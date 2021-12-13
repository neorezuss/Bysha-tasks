package application.handlers;

import java.util.List;

public class PascalTriangleHandler implements Handler {
    @Override
    public String handle(List<Integer> numbers) {
        int firstPositiveNumber = -1;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > 0) {
                firstPositiveNumber = numbers.get(i);
                break;
            }
        }
        if (firstPositiveNumber != -1) {
            int element;
            StringBuilder pascalTriangle = new StringBuilder("Треугольник паскаля:\n");
            for (int y = 0; y < firstPositiveNumber; y++) {
                for (int i = 0; i < firstPositiveNumber - y; i++) {
                    pascalTriangle.append("   ");
                }
                for (int x = 0; x <= y; x++) {
                    element = factorial(y) / (factorial(x) * factorial(y - x));
                    pascalTriangle.append("   " + element + " ");
                }
                pascalTriangle.append("\n");
            }
            return pascalTriangle.toString();
        } else {
            return "Не удалось построить треугольник Паскаля! В массиве нет положительных чисел!";
        }
    }

    private int factorial(int number) {
        if (number < 0) {
            return -1;
        }
        if (number < 2) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}
