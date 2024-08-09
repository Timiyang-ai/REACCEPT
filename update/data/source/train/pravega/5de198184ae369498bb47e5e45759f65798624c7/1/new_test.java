@Test
    public void testInitialize() {
        @Cleanup
        TestContext context = new TestContext(DEFAULT_CONFIG);

        // Check behavior for non-existent segments (in Storage) that are actually supposed to be empty.
        context.transactionAggregators[0].initialize(TIMEOUT).join();
        Assert.assertFalse("isDeleted() flag not set on metadata for deleted segment.", context.transactionAggregators[0].getMetadata().isDeleted());

        // Check behavior for non-existent segments (in Storage) that are not supposed to be empty.
        val sm3 = (UpdateableSegmentMetadata) context.transactionAggregators[3].getMetadata();
        sm3.setLength(1L);
        sm3.setStorageLength(1L);
        context.transactionAggregators[3].initialize(TIMEOUT).join();
        Assert.assertTrue("isDeleted() flag not set on metadata for deleted segment.", sm3.isDeleted());
        Assert.assertTrue("isDeletedInStorage() flag not set on metadata for deleted segment.", sm3.isDeletedInStorage());

        // Check behavior for already-sealed segments (in storage, but not in metadata)
        context.storage.create(context.transactionAggregators[1].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(writeHandle(context.transactionAggregators[1].getMetadata().getName()), TIMEOUT).join();
        AssertExtensions.assertSuppliedFutureThrows(
                "initialize() succeeded on a Segment is sealed in Storage but not in the metadata.",
                () -> context.transactionAggregators[1].initialize(TIMEOUT),
                ex -> ex instanceof DataCorruptionException);

        // Check behavior for already-sealed segments (in storage, in metadata, but metadata does not reflect Sealed in storage.)
        context.storage.create(context.transactionAggregators[2].getMetadata().getName(), TIMEOUT).join();
        context.storage.seal(writeHandle(context.transactionAggregators[2].getMetadata().getName()), TIMEOUT).join();
        ((UpdateableSegmentMetadata) context.transactionAggregators[2].getMetadata()).markSealed();
        context.transactionAggregators[2].initialize(TIMEOUT).join();
        Assert.assertTrue("isSealedInStorage() flag not set on metadata for storage-sealed segment.", context.transactionAggregators[2].getMetadata().isSealedInStorage());

        // Check the ability to update Metadata.StorageOffset if it is different.
        final int writeLength = 10;
        context.storage.create(context.segmentAggregator.getMetadata().getName(), TIMEOUT).join();
        context.storage.write(writeHandle(context.segmentAggregator.getMetadata().getName()), 0, new ByteArrayInputStream(new byte[writeLength]), writeLength, TIMEOUT).join();
        context.segmentAggregator.initialize(TIMEOUT).join();
        Assert.assertEquals("SegmentMetadata.StorageLength was not updated after call to initialize().", writeLength, context.segmentAggregator.getMetadata().getStorageLength());
    }