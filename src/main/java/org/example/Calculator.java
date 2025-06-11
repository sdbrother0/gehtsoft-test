package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Calculator {

    private static final String ERROR_PARSING = "Parsing input error";
    private static final String DIVISION_BY_ZERO = "Division by zero error";

    private final Map<Character, Integer> operatorMap = Map.of(
            '(', 0,
            '+', 0,
            '-', 0,
            '*', 1,
            '/', 1
    );

    private List<Object> getListForCalc(String input) throws IllegalArgumentException {

        List<Object> outputList = new ArrayList<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        input = input.trim().replaceAll(" +", " ");

        int inputLen = input.length();
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < inputLen; i++) {
            Character c = input.charAt(i);
            if (Character.isDigit(c)) {
                numberBuilder.append(c);
                if (i == inputLen - 1) {
                    outputList.add(Integer.parseInt(numberBuilder.toString()));
                    numberBuilder.setLength(0);
                }
            } else {
                if (!numberBuilder.isEmpty()) {
                    outputList.add(Integer.parseInt(numberBuilder.toString()));
                    numberBuilder.setLength(0);
                }
                switch (c) {
                    case '+', '-', '*', '/':
                        if (c == '-' && input.length() > i + 1 && Character.isDigit(input.charAt(i + 1))) {
                            numberBuilder.append(c);
                        } else {
                            while (!operatorStack.isEmpty() && operatorMap.get(operatorStack.peek()) > operatorMap.get(c)) {
                                outputList.add(operatorStack.pop());
                            }
                            operatorStack.push(c);
                        }
                        break;
                    case '(':
                        operatorStack.push(c);
                        break;
                    case ')':
                        while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                            outputList.add(operatorStack.pop());
                        }
                        operatorStack.pop();
                        break;
                    case ' ':
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal character " + c);
                }

            }
        }

        while (!operatorStack.isEmpty()) {
            outputList.add(operatorStack.pop());
        }
        return outputList;
    }

    public String calc(String input) {
        List<Object> listForCalc;
        try {
            listForCalc = getListForCalc(input);
        } catch (IllegalArgumentException e) {
            return ERROR_PARSING;
        }
        Deque<Object> calculateStack = new ArrayDeque<>();
        int outputLen = listForCalc.size();
        for (int i = 0; i <= outputLen - 1; i++) {
            Object currentElement = listForCalc.get(i);
            if (currentElement instanceof Character operator) {
                if ((!calculateStack.isEmpty() && calculateStack.pop() instanceof Integer b) &&
                    (!calculateStack.isEmpty() && calculateStack.pop() instanceof Integer a)) {
                    switch (operator) {
                        case '+':
                            calculateStack.push(a + b);
                            break;
                        case '-':
                            calculateStack.push(a - b);
                            break;
                        case '*':
                            calculateStack.push(a * b);
                            break;
                        case '/':
                            if (b == 0) {
                                return DIVISION_BY_ZERO;
                            }
                            calculateStack.push(a / b);
                            break;
                        default:
                            break;
                    }
                } else {
                    return ERROR_PARSING;
                }
            } else {
                calculateStack.push(currentElement);
            }
        }
        return Optional.ofNullable(calculateStack.peek()).orElse(ERROR_PARSING).toString();
    }
}
