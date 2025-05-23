@Test(groups = "samples", timeOut = TIMEOUT)
    public void createDatabase_toBlocking() {
        Flux<ResourceResponse<Database>> createDatabaseObservable = client.createDatabase(getDatabaseDefinition(),
                                                                                                     null);

        // toBlocking() converts to a blocking observable.
        // single() gets the only result.
        createDatabaseObservable.single().block();
    }