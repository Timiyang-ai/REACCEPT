    private static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting,
                                                             Map<TableId, LifecycleTransaction> transactions,
                                                             long memoryPoolBytes)
    throws IOException
    {
        long nonRedistributingOffHeapSize = compacting.stream().mapToLong(SSTableReader::getIndexSummaryOffHeapSize).sum();
        return IndexSummaryManager.redistributeSummaries(new IndexSummaryRedistribution(transactions,
                                                                                        nonRedistributingOffHeapSize,
                                                                                        memoryPoolBytes));
    }