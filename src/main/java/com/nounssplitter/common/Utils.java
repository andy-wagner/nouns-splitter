package com.nounssplitter.common;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Utils {

    public static LinkedHashSet<String> buildDictionary() {
        System.out.println("Loading german dictionary...");

        LinkedHashSet<String> dictionary = new LinkedHashSet<String>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File dictionaryFile = new File(classLoader
                .getResource("dictionaries/german-common-nouns.txt")
                .getFile()
        );

        try (Scanner scanner = new Scanner(dictionaryFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dictionary.add(line);
            }
            scanner.close();
            System.out.println("German dictionary loaded!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }

}
