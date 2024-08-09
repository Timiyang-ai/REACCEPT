@Test
    public void testCreateNewStreamSegment() {
        final int segmentCount = 10;
        final int transactionsPerSegment = 5;

        @Cleanup
        TestContext context = new TestContext();
        setupOperationLog(context);

        HashSet<String> storageSegments = new HashSet<>();
        setupStorageCreateHandler(context, storageSegments);
        setupStorageGetHandler(context, storageSegments, segmentName -> new StreamSegmentInformation(segmentName, 0, false, false, new ImmutableDate()));

        // Create some Segments and Transaction and verify they are properly created and registered.
        for (int i = 0; i < segmentCount; i++) {
            String name = getName(i);
            context.mapper.createNewStreamSegment(name, TIMEOUT).join();
            assertStreamSegmentCreated(name, context);

            for (int j = 0; j < transactionsPerSegment; j++) {
                String transactionName = context.mapper.createNewTransactionStreamSegment(name, UUID.randomUUID(), TIMEOUT).join();
                assertTransactionCreated(transactionName, name, context);
            }
        }
    }