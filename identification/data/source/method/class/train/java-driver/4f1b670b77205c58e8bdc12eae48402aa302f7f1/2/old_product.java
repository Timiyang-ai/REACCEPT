public Truncate truncate(String keyspace, String table) {
        return new Truncate(protocolVersion, codecRegistry, keyspace, table);
    }