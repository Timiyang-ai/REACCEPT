public static Batch batch(RegularStatement... statements) {
        return new Batch(statements, true);
    }