public Truncate truncate(String keyspace, String table) {
        return new Truncate(cluster, keyspace, table);
    }