public Update update(String table) {
        return new Update(protocolVersion, codecRegistry, null, table);
    }