@DistributedLogAnnotations.FlakyTest
    @Ignore
    @Test(timeout = 60000)
    @SuppressWarnings("deprecation")
    public void testChangeSequenceNumber() throws Exception {
        DistributedLogConfiguration confLocal = new DistributedLogConfiguration();
        confLocal.addConfiguration(conf);
        confLocal.setLogSegmentSequenceNumberValidationEnabled(false);
        confLocal.setLogSegmentCacheEnabled(false);

        DistributedLogConfiguration readConf = new DistributedLogConfiguration();
        readConf.addConfiguration(conf);
        readConf.setLogSegmentCacheEnabled(false);
        readConf.setLogSegmentSequenceNumberValidationEnabled(true);

        URI uri = createDLMURI("/change-sequence-number");
        zooKeeperClient.get().create(uri.getPath(), new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Namespace namespace = NamespaceBuilder.newBuilder()
                .conf(confLocal)
                .uri(uri)
                .build();
        Namespace readNamespace = NamespaceBuilder.newBuilder()
                .conf(readConf)
                .uri(uri)
                .build();

        String streamName = "change-sequence-number";

        // create completed log segments
        DistributedLogManager dlm = namespace.openLog(streamName);
        DLMTestUtil.generateCompletedLogSegments(dlm, confLocal, 4, 10);
        DLMTestUtil.injectLogSegmentWithGivenLogSegmentSeqNo(dlm, confLocal, 5, 41, false, 10, true);
        dlm.close();

        // create a reader
        DistributedLogManager readDLM = readNamespace.openLog(streamName);
        AsyncLogReader reader = readDLM.getAsyncLogReader(DLSN.InitialDLSN);

        // read the records
        long expectedTxId = 1L;
        DLSN lastDLSN = DLSN.InitialDLSN;
        for (int i = 0; i < 4 * 10; i++) {
            LogRecordWithDLSN record = Utils.ioResult(reader.readNext());
            assertNotNull(record);
            DLMTestUtil.verifyLogRecord(record);
            assertEquals(expectedTxId, record.getTransactionId());
            expectedTxId++;
            lastDLSN = record.getDlsn();
        }

        LOG.info("Injecting bad log segment '3'");

        dlm = namespace.openLog(streamName);
        DLMTestUtil.injectLogSegmentWithGivenLogSegmentSeqNo(dlm, confLocal, 3L, 5 * 10 + 1, true, 10, false);

        LOG.info("Injected bad log segment '3'");

        // there isn't records should be read
        CompletableFuture<LogRecordWithDLSN> readFuture = reader.readNext();
        try {
            LogRecordWithDLSN record = Utils.ioResult(readFuture);
            fail("Should fail reading next record "
                    + record
                    + " when there is a corrupted log segment");
        } catch (UnexpectedException ue) {
            // expected
        }

        LOG.info("Dryrun fix inprogress segment that has lower sequence number");

        // Dryrun
        DistributedLogAdmin.fixInprogressSegmentWithLowerSequenceNumber(namespace,
                new DryrunLogSegmentMetadataStoreUpdater(confLocal, getLogSegmentMetadataStore(namespace)), streamName, false, false);

        try {
            reader = readDLM.getAsyncLogReader(lastDLSN);
            Utils.ioResult(reader.readNext());
            fail("Should fail reading next when there is a corrupted log segment");
        } catch (UnexpectedException ue) {
            // expected
        }

        LOG.info("Actual run fix inprogress segment that has lower sequence number");

        // Actual run
        DistributedLogAdmin.fixInprogressSegmentWithLowerSequenceNumber(namespace,
                LogSegmentMetadataStoreUpdater.createMetadataUpdater(confLocal, getLogSegmentMetadataStore(namespace)), streamName, false, false);

        // be able to read more after fix
        reader = readDLM.getAsyncLogReader(lastDLSN);
        // skip the first record
        Utils.ioResult(reader.readNext());
        readFuture = reader.readNext();

        expectedTxId = 51L;
        LogRecord record = Utils.ioResult(readFuture);
        assertNotNull(record);
        DLMTestUtil.verifyLogRecord(record);
        assertEquals(expectedTxId, record.getTransactionId());
        expectedTxId++;

        for (int i = 1; i < 10; i++) {
            record = Utils.ioResult(reader.readNext());
            assertNotNull(record);
            DLMTestUtil.verifyLogRecord(record);
            assertEquals(expectedTxId, record.getTransactionId());
            expectedTxId++;
        }

        Utils.close(reader);
        readDLM.close();

        dlm.close();
        namespace.close();
        readNamespace.close();
    }