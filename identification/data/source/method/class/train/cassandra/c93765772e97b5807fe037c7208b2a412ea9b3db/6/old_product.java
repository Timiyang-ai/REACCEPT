@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting, Map<UUID, LifecycleTransaction> transactions, long memoryPoolBytes) throws IOException
    {
        return CompactionManager.instance.runIndexSummaryRedistribution(new IndexSummaryRedistribution(compacting, transactions, memoryPoolBytes));
    }