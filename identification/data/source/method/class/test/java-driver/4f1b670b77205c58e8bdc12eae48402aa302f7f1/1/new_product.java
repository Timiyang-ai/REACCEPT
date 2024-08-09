public Truncate truncate(TableMetadata table) {
        return new Truncate(cluster, table);
    }