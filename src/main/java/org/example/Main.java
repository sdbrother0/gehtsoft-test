package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final String WELCOME_TEST = """
            Welcome to Gehtsoft Technical Assessment
            Please choose an option:
            1. Caesar Cipher Encryption
            2. Caesar Cipher Decryption
            3. Arithmetic Expression Evaluation
            4. Exit
            """;

    private static final String ENTER_CHOICE = "Enter your choice: ";

    public static void main(String[] args) {

        Caesar caesar = new Caesar();
        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println(WELCOME_TEST);
        System.out.print(ENTER_CHOICE);

        int choice = scanner.nextInt();
        do {
            switch (choice) {
                case 1, 2:
                    System.out.print("Enter text to " + (choice == 1 ? "en" : "de") + "crypt: ");
                    String inputText = scanner.next();
                    System.out.print("Enter shift value: ");
                    String inputShift = scanner.next();

                    int shift = 0;
                    try {
                        shift = Integer.parseInt(inputShift);
                    } catch (NumberFormatException e) {
                        System.out.println("Shift value must be an integer. Use: 0");
                    }

                    Path path = Path.of(inputText);
                    if (Files.exists(path)) {
                        try {
                            System.out.println("Reading from file: " + path.toAbsolutePath());
                            inputText = new String(Files.readAllBytes(path));
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }
                    }

                    String result = caesar.shift(inputText, shift, choice == 1 ? 'e' : 'd');
                    System.out.println("Result: " + result);
                    choice = again(scanner, choice);
                    break;
                case 3:
                    System.out.print("Input: ");
                    String input = scanner.next();
                    String output = calculator.calc(input);
                    System.out.println("Output: " + output);
                    choice = again(scanner, choice);
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

    private static int again(Scanner scanner, int yesCode) {
        System.out.print("Continue? (y/n): ");
        String continueYes = scanner.next();
        if (continueYes.toLowerCase().trim().equals("y")) {
            return yesCode;
        } else {
            System.out.println(WELCOME_TEST);
            System.out.print(ENTER_CHOICE);
            return scanner.nextInt();
        }
    }

}