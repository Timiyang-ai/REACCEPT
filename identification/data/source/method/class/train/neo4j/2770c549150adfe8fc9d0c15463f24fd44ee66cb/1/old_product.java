public void markAsOnline() throws IOException
    {
        ensureOpen();
        commitCloseLock.lock();
        try
        {
            IndexPartition partition = getFirstPartition( getPartitions() );
            IndexWriter indexWriter = partition.getIndexWriter();
            indexWriter.setCommitData( ONLINE_COMMIT_USER_DATA );
            flush();
        }
        finally
        {
            commitCloseLock.unlock();
        }
    }