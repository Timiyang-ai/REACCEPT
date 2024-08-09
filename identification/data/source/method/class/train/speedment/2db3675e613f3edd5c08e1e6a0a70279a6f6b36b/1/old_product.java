public State previous() {
        if (this == STARTED) {
            throw new NoSuchElementException();
        }
        return values()[ordinal() - 1];
    }