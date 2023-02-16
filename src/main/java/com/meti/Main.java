package com.meti;

import java.util.Optional;
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
            var identifier = parseIdentifier(line, "find card ");
            parseIndex(registry, identifier).ifPresentOrElse(
                    card -> printCard(identifier, card),
                    () -> handleNoCardFound(identifier));
        } else if (line.startsWith("update card ")) {
            var identifier = parseIdentifier(line, "update card ");

            System.out.println("Enter in a new title, or a blank one to not change anything.");
            var title = scanner.nextLine();

            System.out.println("Enter in a new description, or a blank one to not change anything.");
            var description = scanner.nextLine();

            var builder = new Card.Partial();
            if (!title.isBlank()) builder = builder.withTitle(title);
            if (!description.isBlank()) builder = builder.withDescription(description);

            try {
                return registry.update(identifier, builder);
            } catch (RegistryException e) {
                System.err.println("Failed to update registry.");
                System.err.println(e.getMessage());
            }
        }
        return registry;
    }

    private static void handleNoCardFound(IntIdentifier identifier) {
        var format = "No card found for ID: '%s'%n";
        System.err.printf(format, identifier);
    }

    private static Optional<Card> parseIndex(CardRegistry registry, IntIdentifier identifier) {
        try {
            return registry.find(identifier);
        } catch (NumberFormatException e) {
            System.err.println("Invalid index format, please try again!");
            return Optional.empty();
        }
    }

    private static IntIdentifier parseIdentifier(String line, String prefix) {
        var index = line.substring(prefix.length());
        return new IntIdentifier(Integer.parseInt(index));
    }

    private static void printCard(Identifier identifier, Card card) {
        System.out.println(identifier + " " + card.computeTitle());
    }
}