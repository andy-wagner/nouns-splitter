package com.nounssplitter.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Utils {

    private static LinkedHashSet<String> dictionaryLoaded = null;

    public static LinkedHashSet<String> buildDictionary() {
        LinkedHashSet<String> dictionary;

        if (dictionaryLoaded == null) {
            System.out.println("Loading german dictionary...");

            dictionary = new LinkedHashSet<String>();

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File dictionaryFile = new File(classLoader
                    .getResource("dictionaries/german-common-nouns.txt")
                    .getFile()
            );

            try {
                Scanner scanner = new Scanner(dictionaryFile);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    dictionary.add(line);
                }
                scanner.close();
                dictionaryLoaded = dictionary;
                System.out.println("German dictionary loaded!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            dictionary = dictionaryLoaded;
        }

        return dictionary;
    }

    public static boolean hasSameSize (String userInput,
            ArrayList<String> nounsList) {
        String nounsListJoined = StringUtils.join(nounsList, "");
        return userInput.length() == nounsListJoined.length();
    }

}
