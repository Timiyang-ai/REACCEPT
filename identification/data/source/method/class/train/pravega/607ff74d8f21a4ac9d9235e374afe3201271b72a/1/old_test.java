@Test
    public void testInitialize() {
        @Cleanup
        TestContext context = new TestContext(DEFAULT_CONFIG);

        // Check behavior for non-existent segments (in Storage).
        context.transactionAggregators[0].initialize(TIMEOUT).join();
        Assert.assertTrue("isDeleted() flag not set on metadata for deleted segment.", context.transactionAggregators[0].getMetadata().isDeleted());

        // Check behavior for already-sealed segments (in storage, but not in metadata)
        val handle1 = context.storage.create(context.transactionAggregators[1].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(handle1, TIMEOUT).join();
        AssertExtensions.assertThrows(
                "initialize() succeeded on a Segment is sealed in Storage but not in the metadata.",
                () -> context.transactionAggregators[1].initialize(TIMEOUT),
                ex -> ex instanceof DataCorruptionException);

        // Check behavior for already-sealed segments (in storage, in metadata, but metadata does not reflect Sealed in storage.)
        val handle2 = context.storage.create(context.transactionAggregators[2].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(handle2, TIMEOUT).join();
        ((UpdateableSegmentMetadata) context.transactionAggregators[2].getMetadata()).markSealed();
        context.transactionAggregators[2].initialize(TIMEOUT).join();
        Assert.assertTrue("isSealedInStorage() flag not set on metadata for storage-sealed segment.", context.transactionAggregators[2].getMetadata().isSealedInStorage());

        // Check the ability to update Metadata.StorageOffset if it is different.
        final int writeLength = 10;
        val handle3 = context.storage.create(context.segmentAggregator.getMetadata().getName(), TIMEOUT).join();
        context.storage.write(handle3, 0, new ByteArrayInputStream(new byte[writeLength]), writeLength, TIMEOUT).join();
        context.segmentAggregator.initialize(TIMEOUT).join();
        Assert.assertEquals("SegmentMetadata.StorageLength was not updated after call to initialize().", writeLength, context.segmentAggregator.getMetadata().getStorageLength());
    }