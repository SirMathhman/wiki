package com.meti;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<String, Card> nodes;

    public CardRegistry() {
        this(Collections.emptyMap());
    }

    public CardRegistry(Map<String, Card> nodes) {
        this.nodes = nodes;
    }

    public CardRegistry create(Card card) throws RegistryException {
        var title = card.computeTitle();
        if (nodes.containsKey(title)) {
            var format = "Duplicate card of title '%s' cannot be re-registered.";
            var message = format.formatted(title);
            throw new RegistryException(message);
        }

        var copy = new HashMap<>(nodes);
        copy.put(title, card);
        return new CardRegistry(copy);
    }
}
