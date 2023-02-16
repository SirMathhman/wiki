package com.meti;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application starting.");

        var scanner = new Scanner(System.in);
        var registry = new CardRegistry();
        do {
            var line = scanner.nextLine();
            if (line.equals("quit")) {
                break;
            }

            registry = processLine(scanner, registry, line);
        } while (true);

        System.out.println("Application stopping.");
    }

    private static CardRegistry processLine(Scanner scanner, CardRegistry registry, String line) {
        // Here's where we'll eventually extract a REST API./
        //POST /card
        if (line.equals("create card")) {
            System.out.println("Enter in title:");
            var title = scanner.nextLine();

            System.out.println("Enter in a description:");
            var description = scanner.nextLine();

            var tuple = registry.create(new Card(title, description));
            System.out.println("Card created with identifier: " + tuple.b());
            return tuple.a();
        }
        //GET /card
        else if (line.equals("list card")) {
            registry.streamCards().forEach(identifier -> {
                var card = registry.find(identifier).orElseThrow();
                printCard(identifier, card);
            });
        }
        //GET /card:id
        else if (line.startsWith("find card ")) {
            var index = line.substring("find card ".length());
            try {
                var identifier = new IntIdentifier(Integer.parseInt(index));
                registry.find(identifier).ifPresentOrElse(
                        card -> printCard(identifier, card),
                        () -> {
                            var format = "No card found for ID: '%s'%n";
                            System.err.printf(format, identifier);
                        });
            } catch (NumberFormatException e) {
                System.err.println("Invalid index format, please try again!");
            }
        }
        return registry;
    }

    private static void printCard(Identifier identifier, Card card) {
        System.out.println(identifier + " " + card.computeTitle());
    }
}