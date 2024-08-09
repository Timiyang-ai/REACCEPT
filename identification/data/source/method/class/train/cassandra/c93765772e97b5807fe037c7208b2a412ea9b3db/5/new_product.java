@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting, List<SSTableReader> nonCompacting, long memoryPoolBytes) throws IOException
    {
        return CompactionManager.instance.runIndexSummaryRedistribution(new IndexSummaryRedistribution(compacting, nonCompacting, memoryPoolBytes));
    }