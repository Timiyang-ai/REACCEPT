public QueryGraph lookup(QueryResult fromRes, QueryResult toRes) {
        List<QueryResult> results = new ArrayList<QueryResult>(2);
        results.add(fromRes);
        results.add(toRes);
        lookup(results);
        return this;
    }