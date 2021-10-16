package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите выражение, состоящее из двух целых чисел от 0 до 10 и знака операции (+,-,/,*) через пробелы. Допустимы арабские и римские числа.");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] tokens = text.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*) через пробелы.");
        }
        int num1, num2;
        String operator = tokens[1];
        String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
        boolean flag = false, flag2 = false;
        for (String s : roman) {
            if (s.equals(tokens[0])) {
                flag = true;
            }
            if (s.equals(tokens[2])) {
                flag2 = true;
            }
        }
        if (flag && flag2) {
            num1 = ConvertManager.romanToArabic(tokens[0]);
            num2 = ConvertManager.romanToArabic(tokens[2]);
            int result = Calculator.calc(num1, num2, operator);
            System.out.println("Ваш ответ " + ConvertManager.arabicToRoman(result));
        } else {
            try {
                num1 = Integer.parseInt(tokens[0]);
                num2 = Integer.parseInt(tokens[2]);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("используются одновременно разные системы счисления");
            }
            if ((num1 > 10 || num1 < 1) || (num2 > 10 || num2 < 1)) {
                throw new IllegalArgumentException("Допустимы только целые числа от 1 до 10");
            }
            int result = Calculator.calc(num1, num2, operator);
            System.out.println("Ваш ответ " + result);
        }
    }
}

class Calculator{
    public static int calc (int num1, int num2, String operator){
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*) через пробелы.");
        };
    }
}

class ConvertManager {
    public static String arabicToRoman(int number) {
        if (number < 1){
            throw new IllegalArgumentException("Результатом вычисления римскими цифрами могут быть только положительные целые числа");
        }
        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i += 1) {
            while (number >= roman_value_list[i]) {
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }
    public static int romanToArabic(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }
}