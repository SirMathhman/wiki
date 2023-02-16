package com.meti;

import java.util.Optional;

public class Card {
    private final String title;
    private final String description;

    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Card update(Partial partial) {
        return new Card(
                partial.findTitle().orElse(title),
                partial.findDescription().orElse(description));
    }

    public String computeTitle() {
        return title;
    }

    private class Partial {
        private final String title;
        private final String description;

        public Partial() {
            this(null, null);
        }

        public Partial(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public Partial withTitle(String title) {
            return new Partial(title, description);
        }

        public Partial withDescription(String description) {
            return new Partial(title, description);
        }

        public Optional<String> findTitle() {
            return Optional.ofNullable(title);
        }

        public Optional<String> findDescription() {
            return Optional.ofNullable(description);
        }
    }
}
