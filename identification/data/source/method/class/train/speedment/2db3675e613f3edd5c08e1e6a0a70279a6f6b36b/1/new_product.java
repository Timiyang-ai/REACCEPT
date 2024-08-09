public State previous() {
        if (this == CREATED) {
            throw new NoSuchElementException();
        }
        return values()[ordinal() - 1];
    }