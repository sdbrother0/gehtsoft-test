### Project description

Implemented two console applications that provides two main functionalities:
1. Caesar Cipher encryption/decryption
2. Arithmetic expression evaluator

### How to compile application:
```
./gradlew clean build

> Task :test

org.example.CaesarTest

getResult(String, int, String, char)

    Test test: "Hello World", shift: "3", expected: "Khoor Zruog", mode:"e" PASSED
    Test test: "Khoor Zruog", shift: "3", expected: "Hello World", mode:"d" PASSED
    Test test: "я", shift: "3", expected: "в", mode:"e" PASSED
    Test test: "Привет World", shift: "5", expected: "Фхнжйч Btwqi", mode:"e" PASSED
    Test test: "Привет Мир", shift: "5", expected: "Фхнжйч Снх", mode:"e" PASSED

org.example.CalculatorTest

getResult(String, String)

    Test test: "2 + 3 * 4", expected: "14" PASSED
    Test test: "(10 + 5) / 3", expected: "5" PASSED
    Test test: "2 * (3 + 4) - 1", expected: "13" PASSED
    Test test: "-5 + 3", expected: "-2" PASSED
    Test test: "2 * ((3 + 4) - 1)", expected: "12" PASSED
    Test test: "(10 + 5) / 4", expected: "3" PASSED
    Test test: "((1 + 2) + 7) * 3", expected: "30" PASSED
    Test test: "3 / 0", expected: "Division by zero error" PASSED
    Test test: "3 / (5 - 5)", expected: "Division by zero error" PASSED
    Test test: "(1", expected: "Parsing input error" PASSED

SUCCESS: Executed 15 tests in 421ms


[Incubating] Problems report is available at: file:///Users/sergeidemin/gehtsoft-test/build/reports/problems/problems-report.html

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.13/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 992ms
8 actionable tasks: 8 executed
```

### How to compile and run the application

```
./gradlew build && java -jar ./build/libs/test-1.0-SNAPSHOT.jar
```

### My approach and any assumptions made

1. Nothing special, the program works as described in the task 
2. I implemented unit tests
3. I found errors in the test cases of the task:
```
Error in the test example from the task:
    Input:  "Привет Мир", shift: 5
    Output: "Хумёзй Рну" //Wrong! See russian alphabet
Correct answer:
    Output: "Фхнжйч Снх"
    1. П -> Ф РСТУФ   5
    2. р -> х стуфх   5
    3. и -> н йклмн   5
    4. в -> ж гдеёж   5
    5. е -> й ёжзий   5
    6. т -> ч уфхцч   5
    7. М -> С НОПРС   5
    8. и -> н йклмн   5
    9. р -> х стуфх   5

Wrong test case from test task:
    1. П -> Х РСТУФХ  6
    2. р -> у сту     3
    3. и -> м йклм    4
    4. в -> ё гдеё    4
    5. е -> з ёжз     3
    6. т -> й уфхцчшщъыьэюяабвгдеёжзий 24
    7. М -> Р НОПР    4
    8. и -> н йклмн   5 (3.)
    9. р -> у сту     3 (2.)
```

### Examples of usage

```
java -jar ./build/libs/test-1.0-SNAPSHOT.jar

Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice: 1
Enter text to encrypt: Hello World
Enter shift value: 3
Result: Khoor Zruog
Continue? (y/n): y
Enter text to encrypt: Test
Enter shift value: 0
Result: Test
Continue? (y/n): n
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice: 2
Enter text to decrypt: Khoor Zruog
Enter shift value: 3
Result: Hello World
Continue? (y/n): n
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice: 1
Enter text to encrypt: /Users/sergeidemin/Desktop/1.txt
Enter shift value: 3
Reading from file: /Users/sergeidemin/Desktop/1.txt
Result: Khoor Zruog

Continue? (y/n): n
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice: 3
Input: 2 + 3 * 4
Output: 14
Continue? (y/n): y
Input: (10 + 5) / 3
Output: 5
Continue? (y/n): y
Input: 2 * ((3 + 4) - 1)
Output: 12
Continue? (y/n): y
Input: 3 / (5 - 5)
Output: Division by zero error
Continue? (y/n): y
Input: (1
Output: Parsing input error
Continue? (y/n): n
Welcome to Gehtsoft Technical Assessment
Please choose an option:
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

Enter your choice: 4
```