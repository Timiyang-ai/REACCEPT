public Batch batch(RegularStatement... statements) {
        return new Batch(protocolVersion, codecRegistry, statements, true);
    }