public Update update(String table) {
        return new Update(cluster, null, table);
    }