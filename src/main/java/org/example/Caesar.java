package org.example;

    /*
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

        Why wrong from test task?:
            1. П -> Х РСТУФХ  6
            2. р -> у сту     3
            3. и -> м йклм    4
            4. в -> ё гдеё    4
            5. е -> з ёжз     3
            6. т -> й уфхцчшщъыьэюяабвгдеёжзий 24
            7. М -> Р НОПР    4
            8. и -> н йклмн   5 (3.)
            9. р -> у сту     3 (2.)
    */

public class Caesar {

    private static final String UPPER_CASE_LETTERS_RUS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String LOWER_CASE_LETTERS_RUS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String shift(String input, int shift, char mode) {
        int shiftMode = shift * ((mode == 'e') ? 1 : -1);
        char[] chars = input.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            chars[i] = getShiftRus(chars[i], UPPER_CASE_LETTERS_RUS, shiftMode); //Some alphabet characters are in the wrong order in the UTF8 table (Ё)
            chars[i] = getShiftRus(chars[i], LOWER_CASE_LETTERS_RUS, shiftMode); //Some alphabet characters are in the wrong order in the UTF8 table (ё)
            chars[i] = getShiftEng(chars[i], 'A', 'Z', shiftMode);
            chars[i] = getShiftEng(chars[i], 'a', 'z', shiftMode);
        }
        return new String(chars);
    }

    private char getShiftRus(Character c, String alphabet, int shift) {
        int indexOf = alphabet.indexOf(c);
        if (indexOf != -1) {
            return alphabet.charAt((indexOf + shift) % alphabet.length());
        }
        return c;
    }

    private char getShiftEng(Character c, char from, char to, int shift) {
        if (c >= from && c <= to) {
            return (char) ((c - from + shift) % 26 + from);
        }
        return c;
    }

}
