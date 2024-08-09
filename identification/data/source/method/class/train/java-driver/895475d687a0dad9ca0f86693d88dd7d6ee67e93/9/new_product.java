public Update update(String keyspace, String table) {
        return new Update(protocolVersion, codecRegistry, keyspace, table);
    }