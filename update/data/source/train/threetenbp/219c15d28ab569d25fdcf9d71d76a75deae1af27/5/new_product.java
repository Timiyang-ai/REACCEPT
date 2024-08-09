@Override
    public <R> R query(Query<R> query) {
        if (query == Query.ZONE_ID || query == Query.CHRONO) {
            return null;
        }
        return query.doQuery(this);
    }