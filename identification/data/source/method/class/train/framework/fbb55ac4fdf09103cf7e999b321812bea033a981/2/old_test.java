    @Test
    public void refresh_dataProviderListenerCallsRefreshInDataGeneartors() {
        session.lock();

        UI ui = new TestUI(session);

        TestDataCommunicator communicator = new TestDataCommunicator();
        communicator.extend(ui);

        TestDataProvider dataProvider = new TestDataProvider();
        communicator.setDataProvider(dataProvider, null);

        TestDataGenerator generator = new TestDataGenerator();
        communicator.addDataGenerator(generator);

        // Generate initial data.
        communicator.beforeClientResponse(true);
        assertEquals("DataGenerator generate was not called", TEST_OBJECT,
                generator.generated);
        generator.generated = null;

        // Make sure data does not get re-generated
        communicator.beforeClientResponse(false);
        assertEquals("DataGenerator generate was called again", null,
                generator.generated);

        // Refresh a data object to trigger an update.
        dataProvider.refreshItem(TEST_OBJECT);

        assertEquals("DataGenerator refresh was not called", TEST_OBJECT,
                generator.refreshed);

        // Test refreshed data generation
        communicator.beforeClientResponse(false);
        assertEquals("DataGenerator generate was not called", TEST_OBJECT,
                generator.generated);
    }