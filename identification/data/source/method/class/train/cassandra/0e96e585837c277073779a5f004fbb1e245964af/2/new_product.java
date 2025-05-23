private void cleanup() throws ExecutionException, InterruptedException
    {
        ColumnFamilyStore cfs = Keyspace.open(SystemKeyspace.NAME).getColumnFamilyStore(SystemKeyspace.BATCHLOG_TABLE);
        cfs.forceBlockingFlush();
        Collection<Descriptor> descriptors = new ArrayList<>();
        for (SSTableReader sstr : cfs.getSSTables())
            descriptors.add(sstr.descriptor);
        if (!descriptors.isEmpty()) // don't pollute the logs if there is nothing to compact.
            CompactionManager.instance.submitUserDefined(cfs, descriptors, Integer.MAX_VALUE).get();
    }