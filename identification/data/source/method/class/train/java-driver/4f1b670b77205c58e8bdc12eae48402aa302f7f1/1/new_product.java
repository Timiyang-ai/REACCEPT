public Truncate truncate(TableMetadata table) {
        return new Truncate(protocolVersion, codecRegistry, table);
    }