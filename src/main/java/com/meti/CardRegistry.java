package com.meti;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<Integer, Card> nodes;

    public CardRegistry() {
        this(Collections.emptyMap());
    }

    public CardRegistry(Map<Integer, Card> nodes) {
        this.nodes = nodes;
    }

    public CardRegistry create(Card card) throws RegistryException {
        var title = card.computeTitle();
        if (hasCardByTitle(title)) {
            var format = "Duplicate card of title '%s' cannot be re-registered.";
            var message = format.formatted(title);
            throw new RegistryException(message);
        }

        var nextKey = nodes.keySet()
                .stream()
                .mapToInt(value -> value)
                .min()
                .orElse(0);

        var copy = new HashMap<>(nodes);
        copy.put(nextKey, card);
        return new CardRegistry(copy);
    }

    private boolean hasCardByTitle(String title) {
        return nodes.values()
                .stream()
                .map(Card::computeTitle)
                .anyMatch(title::equals);
    }
}
