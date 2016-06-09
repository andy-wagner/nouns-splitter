package com.nounssplitter.common;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class NounsHandler {

    private static int lastIndex = 0;

    public static void process(String userInput, Scanner scanner)
        throws IllegalArgumentException {
        LinkedHashSet<String> dictionary = null;
        if (!StringUtils.isBlank(userInput)) {
            dictionary = Utils.buildDictionary();
            StringBuilder tempTerm = new StringBuilder();
            String lastNounFound = "";
            ArrayList<String> nounsList = new ArrayList<String>();

            System.out.println("\nSearching...");

//            for (int i = 0; i < userInput.length(); i++ ) {
//                Character letter = userInput.charAt(i);
//                // To avoid case sensitive issues
//                tempTerm.append(letter.toString().toLowerCase());
//                if (dictionary.contains(tempTerm.toString())) {
//                    nounsList.add(tempTerm.toString());
//                    tempTerm.setLength(0);
//                } else {
//                    continue;
//                }
//            }
            extractNounsFromTerm(0, userInput, dictionary, tempTerm, lastNounFound, nounsList);

            if (!nounsList.isEmpty()
                && Utils.hasSameSize(userInput, nounsList)) {
                System.out.println("You have searched for the following nouns: "
                                    + StringUtils.join(nounsList, ", "));
            } else {
                System.out.println("Oops! There's something wrong...");
                System.out.print("Please, retype the compound noun> ");
                String newUserInput = scanner.nextLine();
//                process(newUserInput, scanner);
            }
        } else {
            System.out.print("What?! Please, next time type something, Bro! ¬¬ > ");
            String newUserInput = scanner.nextLine();
//            process(newUserInput, scanner);
        }
    }

    private static void extractNounsFromTerm(int index, String userInput,
            LinkedHashSet<String> dictionary, StringBuilder tempTerm,
            String lastNounFound, ArrayList<String> nounsList) {
        if (index < userInput.length()) {
            System.out.println("Index is: " + index);
            Character letter = userInput.charAt(index);
            // To avoid case sensitive issues
            tempTerm.append(letter.toString().toLowerCase());

//            System.out.println("Lastindex is: " + lastIndex);
            if (lastIndex != 0
                    && index == userInput.length() - 1 && lastIndex < userInput.length()) {
                System.out.println("Here inside persistance layer");
                int tempNextIndex = lastIndex + 1;
                lastIndex = 0;
//                System.out.println("Tempindex is: " + tempIndex);
                nounsList.add(lastNounFound.toString());
                System.out.println(nounsList);
                tempTerm.setLength(0);
                extractNounsFromTerm(tempNextIndex, userInput, dictionary, tempTerm, lastNounFound, nounsList);
            }

            System.out.println("tempTerm is: " + tempTerm.toString());
            if (dictionary.contains(tempTerm.toString())) {
                System.out.println("FOUND A MATCH");
                lastNounFound = tempTerm.toString();
                lastIndex = index;
                if (lastIndex == userInput.length() - 1) {
                    nounsList.add(lastNounFound.toString());
                    System.out.println(nounsList);
                    tempTerm.setLength(0);
                } else {
                    System.out.println("Lastindex is: " + lastIndex);
                    extractNounsFromTerm(index + 1, userInput, dictionary, tempTerm, lastNounFound, nounsList);
                }
            } else {
                if (lastIndex == userInput.length() - 1) {
                    System.out.println("NOT FOUND A MATCH!");
                }
                extractNounsFromTerm(index + 1, userInput, dictionary, tempTerm, lastNounFound, nounsList);
            }

        }
    }

    private static void extractNounsFromTerm(int index, String userInput,
            LinkedHashSet<String> dictionary, StringBuilder tempTerm,
            String lastNounFound, ArrayList<String> nounsList) {
        System.out.println("Index is: " + index);
        if (index < userInput.length()) {
//            System.out.println("Index is: " + index);
            Character letter = userInput.charAt(index);
            // To avoid case sensitive issues
            tempTerm.append(letter.toString().toLowerCase());

//            System.out.println("Lastindex is: " + lastIndex);
            if (lastIndex != 0
                    && index + 1 == userInput.length()) {
                System.out.println("Here inside persistance layer");
                int tempNextIndex = lastIndex + 1;
                lastIndex = 0;
//                System.out.println("Tempindex is: " + tempIndex);
                nounsList.add(lastNounFound.toString());
                System.out.println(nounsList);
                tempTerm.setLength(0);
                extractNounsFromTerm(tempNextIndex, userInput, dictionary, tempTerm, lastNounFound, nounsList);
            }

            System.out.println("tempTerm is: " + tempTerm.toString());
            if (dictionary.contains(tempTerm.toString())) {
                System.out.println("FOUND A MATCH");
//                nounsList.add(tempTerm.toString());
//                tempTerm.setLength(0);
                lastNounFound = tempTerm.toString();
                lastIndex = index;
//                System.out.println("Lastindex is: " + lastIndex);
                if (lastIndex < userInput.length()) {
                    extractNounsFromTerm(index + 1, userInput, dictionary, tempTerm, lastNounFound, nounsList);
                }
            } else {
                extractNounsFromTerm(index + 1, userInput, dictionary, tempTerm, lastNounFound, nounsList);
            }

        }
    }

}
