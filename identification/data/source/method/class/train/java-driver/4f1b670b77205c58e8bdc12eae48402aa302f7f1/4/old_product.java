public static Truncate truncate(String keyspace, String table) {
        return new Truncate(keyspace, table);
    }