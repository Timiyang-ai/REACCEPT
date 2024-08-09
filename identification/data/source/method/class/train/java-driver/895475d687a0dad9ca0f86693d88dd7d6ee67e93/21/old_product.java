public static Update.Builder update(String keyspace, String table) {
        return new Update.Builder(keyspace, table);
    }