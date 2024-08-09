public void markAsOnline() throws IOException
    {
        ensureOpen();
        AbstractIndexPartition partition = getFirstPartition( getPartitions() );
        IndexWriter indexWriter = partition.getIndexWriter();
        indexWriter.setCommitData( ONLINE_COMMIT_USER_DATA );
        flush( false );
    }