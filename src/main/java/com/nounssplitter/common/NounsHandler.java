package com.nounssplitter.common;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class NounsHandler {

    public static void process(String userInput, Scanner scanner) throws IllegalArgumentException {
        if (!StringUtils.isBlank(userInput)) {
            LinkedHashSet<String> dictionary = Utils.buildDictionary();
//            System.out.println(dictionary);
            StringBuilder tempTerm = new StringBuilder();
            ArrayList<String> nounsList = new ArrayList<String>();

            System.out.println("\nSearching...");

            for (int i = 0; i < userInput.length(); i++ ) {
                Character letter = userInput.charAt(i);
                // To avoid case sensitive issues
                tempTerm.append(letter.toString().toLowerCase());
//                System.out.println(tempTerm);
                if (dictionary.contains(tempTerm.toString())) {
                    nounsList.add(tempTerm.toString());
                    System.out.println("This noun exists: " + tempTerm);
                    tempTerm.setLength(0);
                } else {
//                    System.out.println("This noun doesn't exists: " + tempTerm);
                    continue;
                }
            }
        } else {
            System.out.print("What?! Please, next time type something, Bro! ¬¬ > ");
            String newUserInput = scanner.nextLine();
            process(newUserInput, scanner);
        }
    }

}
