@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting, List<SSTableReader> nonCompacting, long memoryPoolBytes) throws IOException
    {
        long total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, nonCompacting))
            total += sstable.getIndexSummaryOffHeapSize();

        List<SSTableReader> oldFormatSSTables = new ArrayList<>();
        for (SSTableReader sstable : nonCompacting)
        {
            // We can't change the sampling level of sstables with the old format, because the serialization format
            // doesn't include the sampling level.  Leave this one as it is.  (See CASSANDRA-8993 for details.)
            logger.trace("SSTable {} cannot be re-sampled due to old sstable format", sstable);
            if (!sstable.descriptor.version.hasSamplingLevel())
                oldFormatSSTables.add(sstable);
        }
        nonCompacting.removeAll(oldFormatSSTables);

        logger.debug("Beginning redistribution of index summaries for {} sstables with memory pool size {} MB; current spaced used is {} MB",
                     nonCompacting.size(), memoryPoolBytes / 1024L / 1024L, total / 1024.0 / 1024.0);

        final Map<SSTableReader, Double> readRates = new HashMap<>(nonCompacting.size());
        double totalReadsPerSec = 0.0;
        for (SSTableReader sstable : nonCompacting)
        {
            if (sstable.getReadMeter() != null)
            {
                Double readRate = sstable.getReadMeter().fifteenMinuteRate();
                totalReadsPerSec += readRate;
                readRates.put(sstable, readRate);
            }
        }
        logger.trace("Total reads/sec across all sstables in index summary resize process: {}", totalReadsPerSec);

        // copy and sort by read rates (ascending)
        List<SSTableReader> sstablesByHotness = new ArrayList<>(nonCompacting);
        Collections.sort(sstablesByHotness, new ReadRateComparator(readRates));

        long remainingBytes = memoryPoolBytes;
        for (SSTableReader sstable : Iterables.concat(compacting, oldFormatSSTables))
            remainingBytes -= sstable.getIndexSummaryOffHeapSize();

        logger.trace("Index summaries for compacting SSTables are using {} MB of space",
                     (memoryPoolBytes - remainingBytes) / 1024.0 / 1024.0);
        List<SSTableReader> newSSTables = adjustSamplingLevels(sstablesByHotness, totalReadsPerSec, remainingBytes);

        total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, oldFormatSSTables, newSSTables))
            total += sstable.getIndexSummaryOffHeapSize();
        logger.debug("Completed resizing of index summaries; current approximate memory used: {} MB",
                     total / 1024.0 / 1024.0);

        return newSSTables;
    }