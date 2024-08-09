private void cleanup() throws ExecutionException, InterruptedException
    {
        ColumnFamilyStore cfs = Keyspace.open(SystemKeyspace.NAME).getColumnFamilyStore(SystemKeyspace.BATCHLOG);
        cfs.forceBlockingFlush();
        Collection<Descriptor> descriptors = new ArrayList<>();
        // expects ALL sstables to be available for compaction, so just use live set...
        for (SSTableReader sstr : cfs.getSSTables(SSTableSet.LIVE))
            descriptors.add(sstr.descriptor);
        if (!descriptors.isEmpty()) // don't pollute the logs if there is nothing to compact.
            CompactionManager.instance.submitUserDefined(cfs, descriptors, Integer.MAX_VALUE).get();
    }