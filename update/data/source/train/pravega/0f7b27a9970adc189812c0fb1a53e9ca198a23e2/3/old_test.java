@Test
    public void testInitialize() {
        @Cleanup
        TestContext context = new TestContext(DEFAULT_CONFIG);

        // Check behavior for non-existent segments (in Storage).
        context.batchesAggregators[0].initialize(TIMEOUT).join();
        Assert.assertTrue("isDeleted() flag not set on metadata for deleted segment.", context.batchesAggregators[0].getMetadata().isDeleted());

        // Check behavior for already-sealed segments (in storage, but not in metadata)
        context.storage.create(context.batchesAggregators[1].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(context.batchesAggregators[1].getMetadata().getName(), TIMEOUT).join();
        AssertExtensions.assertThrows(
                "initialize() succeeded on a Segment is sealed in Storage but not in the metadata.",
                () -> context.batchesAggregators[1].initialize(TIMEOUT),
                ex -> ex instanceof RuntimeStreamingException && ex.getCause() instanceof DataCorruptionException);

        // Check behavior for already-sealed segments (in storage, in metadata, but metadata does not reflect Sealed in storage.)
        context.storage.create(context.batchesAggregators[2].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(context.batchesAggregators[2].getMetadata().getName(), TIMEOUT).join();
        ((UpdateableSegmentMetadata) context.batchesAggregators[2].getMetadata()).markSealed();
        context.batchesAggregators[2].initialize(TIMEOUT).join();
        Assert.assertTrue("isSealedInStorage() flag not set on metadata for storage-sealed segment.", context.batchesAggregators[2].getMetadata().isSealedInStorage());

        // Check the ability to update Metadata.StorageOffset if it is different.
        final int writeLength = 10;
        context.storage.create(context.segmentAggregator.getMetadata().getName(), TIMEOUT).join();
        context.storage.write(context.segmentAggregator.getMetadata().getName(), 0, new ByteArrayInputStream(new byte[writeLength]), writeLength, TIMEOUT).join();
        context.segmentAggregator.initialize(TIMEOUT).join();
        Assert.assertEquals("SegmentMetadata.StorageLength was not updated after call to initialize().", writeLength, context.segmentAggregator.getMetadata().getStorageLength());
    }