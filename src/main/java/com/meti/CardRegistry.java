package com.meti;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardRegistry {
    private final Map<Identifier, Card> cards;
    private final Set<Dependency> dependencies;

    public CardRegistry() {
        this(Collections.emptyMap(), Collections.emptySet());
    }

    public CardRegistry(Map<Identifier, Card> cards, Set<Dependency> dependencies) {
        this.cards = cards;
        this.dependencies = dependencies;
    }

    public CardRegistry delete(Identifier sourceID, Identifier destinationID) throws RegistryException {
        ensureIdentifierOf(sourceID);
        ensureIdentifierOf(destinationID);
        invalidateInvertedDependency(sourceID, destinationID);

        if (!hasDependencyBetween(sourceID, destinationID)) {
            var format = "Dependency does not exist from '%s' to '%s'.";
            var message = format.formatted(sourceID, destinationID);
            throw new RegistryException(message);
        }

        return new CardRegistry(cards, dependencies.stream()
                .flatMap(dependency -> dependency.hasSourceOf(sourceID) &&
                                       dependency.hasDestinationOf(destinationID) ?
                        Stream.empty() :
                        Stream.of(dependency))
                .collect(Collectors.toSet()));
    }

    public CardRegistry create(Identifier sourceID, Identifier destinationID) throws RegistryException {
        ensureIdentifierOf(sourceID);
        ensureIdentifierOf(destinationID);

        if (hasDependencyBetween(sourceID, destinationID)) {
            var format = "Dependency already exists from '%s' to '%s'.";
            var message = format.formatted(sourceID, destinationID);
            throw new RegistryException(message);
        }

        /*
        By the specifications, this should be an acyclic-directed graph.
        Thus, we have to check in the opposite direction as well.
        */
        invalidateInvertedDependency(sourceID, destinationID);

        /*
        TODO: check for total cycle
        There may not be a dependency directly between two cards,
        but it is possible that there may be cards in between.
        */

        var copy = new HashSet<>(dependencies);
        copy.add(new Dependency(sourceID, destinationID));
        return new CardRegistry(cards, copy);
    }

    private void invalidateInvertedDependency(Identifier sourceID, Identifier destinationID) throws RegistryException {
        if (hasDependencyBetween(destinationID, sourceID)) {
            var format = "Dependency exists in inverse from '%s' to '%s'.";
            var message = format.formatted(sourceID, destinationID);
            throw new RegistryException(message);
        }
    }

    private boolean hasDependencyBetween(Identifier sourceID, Identifier destinationID) {
        return dependencies.stream()
                .filter(dependency -> dependency.hasSourceOf(sourceID))
                .anyMatch(dependency -> dependency.hasDestinationOf(destinationID));
    }

    private void ensureIdentifierOf(Identifier sourceID) throws RegistryException {
        if (!cards.containsKey(sourceID)) {
            var format = "No valid card found for id: '%s'.";
            var message = format.formatted(sourceID);
            throw new RegistryException(message);
        }
    }

    public CardRegistry update(Identifier identifier, Card.Partial partial) throws RegistryException {
        if (cards.containsKey(identifier)) {
            var cardCopy = cards.get(identifier).update(partial);
            var copy = new HashMap<>(cards);
            copy.put(identifier, cardCopy);
            return new CardRegistry(copy, dependencies);
        } else {
            var format = "Nothing could be found for identifier: '%s'.";
            var message = format.formatted(identifier);
            throw new RegistryException(message);
        }
    }

    public Optional<Card> find(Identifier identifier) {
        return cards.containsKey(identifier) ?
                Optional.of(cards.get(identifier)) :
                Optional.empty();
    }

    public CardRegistry delete(Identifier identifier) {
        return new CardRegistry(find(identifier)
                .map(found -> {
                    Map<Identifier, Card> copy = new HashMap<>(cards);
                    copy.remove(identifier);
                    return copy;
                })
                .orElse(this.cards), dependencies);
    }

    public Tuple<CardRegistry, Identifier> create(Card card) throws RegistryException {
        var nextKey = cards.keySet()
                .stream()
                .sorted()
                .findFirst()
                .map(Identifier::next)
                .orElse(new IntIdentifier(0));

        var copy = new HashMap<>(cards);
        copy.put(nextKey, card);
        return new Tuple<>(new CardRegistry(copy, dependencies), nextKey);
    }
}
