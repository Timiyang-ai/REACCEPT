public boolean isFlagSet(int bit) {
        return (flags & 1 << bit) != 0;
    }