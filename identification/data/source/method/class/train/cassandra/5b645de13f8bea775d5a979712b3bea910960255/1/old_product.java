void logQuery(String query, QueryOptions queryOptions, long queryTimeMillis)
    {
        Preconditions.checkNotNull(query, "query was null");
        Preconditions.checkNotNull(queryOptions, "queryOptions was null");
        Preconditions.checkArgument(queryTimeMillis > 0, "queryTimeMillis must be > 0");

        //Don't construct the wrapper if the log is disabled
        BinLog binLog = this.binLog;
        if (binLog == null)
        {
            return;
        }

        WeighableMarshallableQuery wrappedQuery = new WeighableMarshallableQuery(query, queryOptions, queryTimeMillis);
        logRecord(wrappedQuery, binLog);
    }