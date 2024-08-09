void logQuery(String query, QueryOptions queryOptions, QueryState queryState, long queryTimeMillis)
    {
        Preconditions.checkNotNull(query, "query was null");
        Preconditions.checkNotNull(queryOptions, "queryOptions was null");
        Preconditions.checkNotNull(queryState, "queryState was null");
        Preconditions.checkArgument(queryTimeMillis > 0, "queryTimeMillis must be > 0");

        //Don't construct the wrapper if the log is disabled
        BinLog binLog = this.binLog;
        if (binLog == null)
        {
            return;
        }

        Query wrappedQuery = new Query(query, queryOptions, queryState, queryTimeMillis);
        logRecord(wrappedQuery, binLog);
    }