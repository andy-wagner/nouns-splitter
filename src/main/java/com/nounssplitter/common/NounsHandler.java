package com.nounssplitter.common;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class NounsHandler {

    public static void process(String userInput, Scanner scanner)
        throws IllegalArgumentException {
        LinkedHashSet<String> dictionary = null;
        if (!StringUtils.isBlank(userInput)) {
            dictionary = Utils.buildDictionary();
            StringBuilder tempTerm = new StringBuilder();
            ArrayList<String> nounsList = new ArrayList<String>();

            System.out.println("\nSearching...");

            for (int i = 0; i < userInput.length(); i++ ) {
                Character letter = userInput.charAt(i);
                // To avoid case sensitive issues
                tempTerm.append(letter.toString().toLowerCase());
                if (dictionary.contains(tempTerm.toString())) {
                    nounsList.add(tempTerm.toString());
                    tempTerm.setLength(0);
                } else {
                    continue;
                }
            }

            if (!nounsList.isEmpty()
                && Utils.hasSameSize(userInput, nounsList)) {
                System.out.println("You have searched for the following nouns: "
                                    + StringUtils.join(nounsList, ","));
            } else {
                System.out.println("Oops! There's something wrong...");
                System.out.print("Please, retype the compound noun> ");
                String newUserInput = scanner.nextLine();
                process(newUserInput, scanner);
            }
        } else {
            System.out.print("What?! Please, next time type something, Bro! ¬¬ > ");
            String newUserInput = scanner.nextLine();
            process(newUserInput, scanner);
        }
    }

}
