@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(IndexSummaryRedistribution redistribution) throws IOException
    {
        return CompactionManager.instance.runIndexSummaryRedistribution(redistribution);
    }