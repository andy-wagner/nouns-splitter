package com.nounssplitter.common;

import java.io.InputStream;
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
            InputStream dictionaryInputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("dictionaries/german-common-nouns.txt");

            Scanner scanner = new Scanner(dictionaryInputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dictionary.add(line);
            }
            scanner.close();
            dictionaryLoaded = dictionary;
            System.out.println("German dictionary loaded!");

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
