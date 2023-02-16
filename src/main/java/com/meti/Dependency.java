package com.meti;

public record Dependency(Identifier source, Identifier destination) {

    public boolean hasSourceOf(Identifier source) {
        return this.source.equals(source);
    }

    public boolean hasDestinationOf(Identifier destination) {
        return this.destination.equals(destination);
    }
}
