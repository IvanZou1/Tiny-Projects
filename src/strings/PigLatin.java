package strings;

import java.util.LinkedList;
import java.util.Scanner;

/*
 *
 * PigLatin - The user enters an English string (word or phrase), and this
 * program outputs the translation of this string in Pig Latin
 *
 * Pig Latin alters the English language by moving the first letter of a word
 * to the end of the word and adding "ay" to the end if the first letter is
 * a consonant, and if the first letter is a vowel then we just add "yay" to
 * the end of the word.
 *
 */

public class PigLatin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Translate to Pig Latin!");
        System.out.print("Enter an English String: ");
        String in = sc.nextLine();
        sc.close();
        System.out.println(in + " translated to pig latin: ");
        printList(toPigLatin(in));
    }

    private static LinkedList<String> toPigLatin(String string) {
        StringBuilder translated;
        LinkedList<String> pigLatin = new LinkedList<>();
        String[] words = string.split(" ");
        for (String word : words) {
            translated = new StringBuilder();
            word = word.toLowerCase().replaceAll(" ", "");
            if (word.length() != 0) {
                if (existFirstLetter(word)) {
                    char firstLetter = word.charAt(0);
                    if (isVowel(firstLetter)) {
                        translated.append(word);
                        translated.append("yay");
                    } else {
                        translated.append(word.substring(1));
                        translated.append(firstLetter);
                        translated.append("ay");
                    }
                } else {
                    translated.append(word);
                }
                pigLatin.add(translated.toString());
            }
        }
        return pigLatin;
    }

    private static boolean existFirstLetter(String word) {
        return Character.isLetter(word.charAt(0));
    }

    private static boolean isVowel(char letter) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        for (char vowel : vowels) {
            if (letter == vowel) {
                return true;
            }
        }
        return false;
    }

    private static void printList(LinkedList<String> list) {
        for (String word : list) {
            System.out.print(word + " ");
        }
    }
}
