public Month minus(long months) {
        return plus(-(months % 12));
    }