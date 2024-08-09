@VisibleForTesting
    public static List<SSTableReader> redistributeSummaries(List<SSTableReader> compacting, Map<UUID, LifecycleTransaction> transactions, long memoryPoolBytes) throws IOException
    {
        logger.info("Redistributing index summaries");
        List<SSTableReader> oldFormatSSTables = new ArrayList<>();
        List<SSTableReader> redistribute = new ArrayList<>();
        for (LifecycleTransaction txn : transactions.values())
        {
            for (SSTableReader sstable : ImmutableList.copyOf(txn.originals()))
            {
                // We can't change the sampling level of sstables with the old format, because the serialization format
                // doesn't include the sampling level.  Leave this one as it is.  (See CASSANDRA-8993 for details.)
                logger.trace("SSTable {} cannot be re-sampled due to old sstable format", sstable);
                if (!sstable.descriptor.version.hasSamplingLevel())
                {
                    oldFormatSSTables.add(sstable);
                    txn.cancel(sstable);
                }
            }
            redistribute.addAll(txn.originals());
        }

        long total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, redistribute))
            total += sstable.getIndexSummaryOffHeapSize();

        logger.trace("Beginning redistribution of index summaries for {} sstables with memory pool size {} MB; current spaced used is {} MB",
                     redistribute.size(), memoryPoolBytes / 1024L / 1024L, total / 1024.0 / 1024.0);

        final Map<SSTableReader, Double> readRates = new HashMap<>(redistribute.size());
        double totalReadsPerSec = 0.0;
        for (SSTableReader sstable : redistribute)
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
        List<SSTableReader> sstablesByHotness = new ArrayList<>(redistribute);
        Collections.sort(sstablesByHotness, new ReadRateComparator(readRates));

        long remainingBytes = memoryPoolBytes;
        for (SSTableReader sstable : Iterables.concat(compacting, oldFormatSSTables))
            remainingBytes -= sstable.getIndexSummaryOffHeapSize();

        logger.trace("Index summaries for compacting SSTables are using {} MB of space",
                     (memoryPoolBytes - remainingBytes) / 1024.0 / 1024.0);
        List<SSTableReader> newSSTables = adjustSamplingLevels(sstablesByHotness, transactions, totalReadsPerSec, remainingBytes);

        for (LifecycleTransaction txn : transactions.values())
            txn.finish();

        total = 0;
        for (SSTableReader sstable : Iterables.concat(compacting, oldFormatSSTables, newSSTables))
            total += sstable.getIndexSummaryOffHeapSize();
        logger.trace("Completed resizing of index summaries; current approximate memory used: {} MB",
                     total / 1024.0 / 1024.0);

        return newSSTables;
    }