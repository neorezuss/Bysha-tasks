package application.handlers;

import application.ArrayOperations;

public class PascalTriangleHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        return "Треугольник паскаля:\n" + arrayOperations.getPascalTriangle();
    }
}
