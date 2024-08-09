public Update update(String keyspace, String table) {
        return new Update(cluster, keyspace, table);
    }