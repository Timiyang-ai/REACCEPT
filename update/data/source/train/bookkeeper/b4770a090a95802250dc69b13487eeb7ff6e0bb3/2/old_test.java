@Test(timeout = 60000)
    public void testWriteControlRecord() throws Exception {
        String name = runtime.getMethodName();
        DistributedLogConfiguration confLocal = new DistributedLogConfiguration();
        confLocal.loadConf(testConf);
        confLocal.setOutputBufferSize(1024);
        DistributedLogManager dlm = createNewDLM(confLocal, name);

        // Write 3 log segments. For each log segments, write one control record and nine user records.
        int txid = 1;
        for (long i = 0; i < 3; i++) {
            final long currentLogSegmentSeqNo = i + 1;
            BKAsyncLogWriter writer = (BKAsyncLogWriter)(dlm.startAsyncLogSegmentNonPartitioned());
            DLSN dlsn = Await.result(writer.writeControlRecord(new LogRecord(txid++, "control".getBytes(UTF_8))));
            assertEquals(currentLogSegmentSeqNo, dlsn.getLogSegmentSequenceNo());
            assertEquals(0, dlsn.getEntryId());
            assertEquals(0, dlsn.getSlotId());
            for (long j = 1; j < 10; j++) {
                final LogRecord record = DLMTestUtil.getLargeLogRecordInstance(txid++);
                Await.result(writer.write(record));
            }
            writer.closeAndComplete();
        }
        dlm.close();

        // Read all the written data: It should skip control records and only return user records.
        DistributedLogManager readDlm = createNewDLM(confLocal, name);
        LogReader reader = readDlm.getInputStream(1);

        long numTrans = 0;
        long expectedTxId = 2;
        LogRecord record = reader.readNext(false);
        while (null != record) {
            DLMTestUtil.verifyLargeLogRecord(record);
            numTrans++;
            assertEquals(expectedTxId, record.getTransactionId());
            if (expectedTxId % 10 == 0) {
                expectedTxId += 2;
            } else {
                ++expectedTxId;
            }
            record = reader.readNext(false);
        }
        reader.close();
        assertEquals(3 * 9, numTrans);
        assertEquals(3 * 9, readDlm.getLogRecordCount());
        readDlm.close();
    }