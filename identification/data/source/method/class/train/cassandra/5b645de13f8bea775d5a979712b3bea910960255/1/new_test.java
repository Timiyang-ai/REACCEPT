    private void logQuery(String query)
    {
        instance.logQuery(query, QueryOptions.DEFAULT, queryState(), 1);
    }