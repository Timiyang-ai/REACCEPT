private void cleanup() throws ExecutionException, InterruptedException
    {
        ColumnFamilyStore cfs = Table.open(Table.SYSTEM_KS).getColumnFamilyStore(SystemTable.BATCHLOG_CF);
        cfs.forceBlockingFlush();
        Collection<Descriptor> descriptors = new ArrayList<Descriptor>();
        for (SSTableReader sstr : cfs.getSSTables())
            descriptors.add(sstr.descriptor);
        if (!descriptors.isEmpty()) // don't pollute the logs if there is nothing to compact.
            CompactionManager.instance.submitUserDefined(cfs, descriptors, Integer.MAX_VALUE).get();
    }