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

            extractNounsFromTerm(0, userInput, dictionary, tempTerm,
                    lastNounFound, nounsList);

            if (!nounsList.isEmpty()
                && Utils.hasSameSize(userInput, nounsList)) {
                System.out.println("You have searched for the following nouns: "
                                    + StringUtils.join(nounsList, ", "));
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

    private static void extractNounsFromTerm(int index, String userInput,
            LinkedHashSet<String> dictionary, StringBuilder tempTerm,
            String lastNounFound, ArrayList<String> nounsList) {
        if (index < userInput.length()) {
            Character letter = userInput.charAt(index);
            // To avoid case sensitive issues
            tempTerm.append(letter.toString().toLowerCase());

            if (lastIndex != 0
                    && index == userInput.length() - 1) {
                int tempNextIndex = lastIndex + 1;
                lastIndex = 0;
                nounsList.add(lastNounFound.toString());
                tempTerm.setLength(0);
                extractNounsFromTerm(tempNextIndex, userInput, dictionary,
                        tempTerm, lastNounFound, nounsList);
            }

            if (dictionary.contains(tempTerm.toString())) {
                lastNounFound = tempTerm.toString();
                lastIndex = index;
                if (lastIndex == userInput.length() - 1) {
                    nounsList.add(lastNounFound.toString());
                    tempTerm.setLength(0);
                } else {
                    extractNounsFromTerm(index + 1, userInput, dictionary,
                            tempTerm, lastNounFound, nounsList);
                }
            } else {
                extractNounsFromTerm(index + 1, userInput, dictionary,
                        tempTerm, lastNounFound, nounsList);
            }

        }
    }

}
