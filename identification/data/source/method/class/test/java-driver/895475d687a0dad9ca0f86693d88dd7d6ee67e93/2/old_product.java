public Update update(TableMetadata table) {
        return new Update(protocolVersion, codecRegistry, table);
    }