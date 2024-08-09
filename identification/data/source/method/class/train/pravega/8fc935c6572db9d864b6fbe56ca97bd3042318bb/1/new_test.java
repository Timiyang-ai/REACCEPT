@Test
    public void testReset() throws Exception {
        // Write some data to the OutputStream (remember it).
        // Reset it.
        // Write more data to the OutputStream.
        // Flush it.
        // Verify the first data is not in.

        int maxFrameSize = 10 * 1024;

        // Callback for when a frame is written.
        AtomicReference<DataFrame> writtenFrame = new AtomicReference<>();
        Consumer<DataFrame> callback = df -> {
            Assert.assertNull("A frame has already been written.", writtenFrame.get());
            writtenFrame.set(df);
        };

        ArrayList<byte[]> records = DataFrameTestHelpers.generateRecords(2, 0, 1024);
        try (DataFrameOutputStream s = new DataFrameOutputStream(maxFrameSize, callback)) {
            // Test 1: write record + reset + flush -> no frame.
            s.startNewRecord();
            s.write(records.get(0));
            s.endRecord();
            s.reset();
            s.flush();
            Assert.assertNull("A frame has been created when flush() was called with no contents (post reset()).", writtenFrame.get());

            // Test 2: write record 1 + reset + write record 2 + flush -> frame with record 2 only.
            s.startNewRecord();
            s.write(records.get(0));
            s.endRecord();
            s.reset();

            s.startNewRecord();
            s.write(records.get(1));
            s.endRecord();
            s.flush();
        }

        Assert.assertNotNull("No frame has been created when flush() was called with contents.", writtenFrame.get());
        Assert.assertTrue("Created frame is not sealed.", writtenFrame.get().isSealed());

        // Verify that the output frame only has record 2.
        records.remove(0);
        DataFrameTestHelpers.checkReadRecords(writtenFrame.get(), records, ByteArraySegment::new);
    }