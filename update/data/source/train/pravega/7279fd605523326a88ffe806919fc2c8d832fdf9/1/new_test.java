@Test
    public void testFlush() throws Exception {
        // Append two records, make sure they are not flushed, close the Builder, then make sure they are flushed.
        try (TestDurableDataLog dataLog = TestDurableDataLog.create(CONTAINER_ID, FRAME_SIZE, executorService())) {
            dataLog.initialize(TIMEOUT);

            ArrayList<TestLogItem> records = DataFrameTestHelpers.generateLogItems(2, SMALL_RECORD_MIN_SIZE, SMALL_RECORD_MAX_SIZE, 0);
            List<DataFrameBuilder.CommitArgs> commitFrames = Collections.synchronizedList(new ArrayList<>());
            BiConsumer<Throwable, DataFrameBuilder.CommitArgs> errorCallback = (ex, a) ->
                    Assert.fail(String.format("Unexpected error occurred upon commit. %s", ex));
            val args = new DataFrameBuilder.Args(Callbacks::doNothing, commitFrames::add, errorCallback, executorService());

            @Cleanup
            DataFrameBuilder<TestLogItem> b = new DataFrameBuilder<>(dataLog, args);
            for (TestLogItem item : records) {
                b.append(item);
            }

            // Check the correctness of the commit callback.
            Assert.assertEquals("A Data Frame was generated but none was expected yet.", 0, commitFrames.size());

            // Invoke flush.
            b.flush();

            // Wait for all the frames commit callbacks to be invoked.
            await(() -> commitFrames.size() >= 1, 20);

            // Check the correctness of the commit callback (after closing the builder).
            Assert.assertEquals("Exactly one Data Frame was expected so far.", 1, commitFrames.size());

            //Read all entries in the Log and interpret them as DataFrames, then verify the records can be reconstructed.
            List<DataFrame> frames = dataLog.getAllEntries(readItem -> DataFrame.from(readItem.getPayload(), readItem.getLength()));
            Assert.assertEquals("Unexpected number of frames generated.", commitFrames.size(), frames.size());
            DataFrameTestHelpers.checkReadRecords(frames, records, r -> new ByteArraySegment(r.getFullSerialization()));
        }
    }