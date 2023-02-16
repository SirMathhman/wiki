package com.meti;

record IntIdentifier(int value) implements Identifier {
    @Override
    public Identifier next() {
        return new IntIdentifier(value + 1);
    }
}
