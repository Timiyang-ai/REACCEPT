public Truncate truncate(String table) {
        return new Truncate(cluster, null, table);
    }