package com.nounssplitter.client;

import java.util.Scanner;

import com.nounssplitter.common.NounsHandler;

public class UIManager {

    public static void start() {
        System.out.println("======== NounsSplitter - The tool! ========");
        System.out.println(" Welcome to the best tool ever made\n");
        System.out.println(" NounsSplitter Corp. presents the next");
        System.out.println(" generation of its nouns splitter solution");
        System.out.println(" The first version are prepared to handle");
        System.out.println(" with german compound nouns, being able");
        System.out.println(" to SPLIT it!\n");

        System.out.println(" - How to use?");
        System.out.println("   0. Find the most difficult german");
        System.out.println("      compound noun.");
        System.out.println("   1. Type this noun down here.");
        System.out.println("   2. Be ready to fall in love with the");
        System.out.println("      NounsSplitter result list.");
        System.out.println("===========================================\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the compound noun> ");
        String userInput = scanner.nextLine();

        try {
            NounsHandler.process(userInput, scanner);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
