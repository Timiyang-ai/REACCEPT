@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting, List<SSTableReader> nonCompacting, long memoryPoolBytes) throws IOException
    {
        long total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, nonCompacting))
            total += sstable.getIndexSummaryOffHeapSize();

        logger.debug("Beginning redistribution of index summaries for {} sstables with memory pool size {} MB; current spaced used is {} MB",
                     nonCompacting.size(), memoryPoolBytes / 1024L / 1024L, total / 1024.0 / 1024.0);

        double totalReadsPerSec = 0.0;
        for (SSTableReader sstable : nonCompacting)
        {
            if (sstable.readMeter != null)
            {
                totalReadsPerSec += sstable.readMeter.fifteenMinuteRate();
            }
        }
        logger.trace("Total reads/sec across all sstables in index summary resize process: {}", totalReadsPerSec);

        // copy and sort by read rates (ascending)
        List<SSTableReader> sstablesByHotness = new ArrayList<>(nonCompacting);
        Collections.sort(sstablesByHotness, new Comparator<SSTableReader>()
        {
            public int compare(SSTableReader o1, SSTableReader o2)
            {
                if (o1.readMeter == null && o2.readMeter == null)
                    return 0;
                else if (o1.readMeter == null)
                    return -1;
                else if (o2.readMeter == null)
                    return 1;
                else
                    return Double.compare(o1.readMeter.fifteenMinuteRate(), o2.readMeter.fifteenMinuteRate());
            }
        });

        long remainingBytes = memoryPoolBytes;
        for (SSTableReader sstable : compacting)
            remainingBytes -= sstable.getIndexSummaryOffHeapSize();

        logger.trace("Index summaries for compacting SSTables are using {} MB of space",
                     (memoryPoolBytes - remainingBytes) / 1024.0 / 1024.0);
        List<SSTableReader> newSSTables = adjustSamplingLevels(sstablesByHotness, totalReadsPerSec, remainingBytes);

        total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, newSSTables))
            total += sstable.getIndexSummaryOffHeapSize();
        logger.debug("Completed resizing of index summaries; current approximate memory used: {} MB",
                     total / 1024.0 / 1024.0);

        return newSSTables;
    }