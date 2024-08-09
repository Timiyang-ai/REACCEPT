public static Batch batch(CQLStatement... statements) {
        return new Batch(statements);
    }