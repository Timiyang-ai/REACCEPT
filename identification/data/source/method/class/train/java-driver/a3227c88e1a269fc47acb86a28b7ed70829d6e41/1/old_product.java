public Batch batch(RegularStatement... statements) {
        return new Batch(cluster, statements, true);
    }