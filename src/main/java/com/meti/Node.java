package com.meti;

import java.util.Optional;

public class Node {
    private final String title;
    private final String description;

    public Node(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Node update(Partial partial) {
        return new Node(
                partial.findTitle().orElse(title),
                partial.findDescription().orElse(description));
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
