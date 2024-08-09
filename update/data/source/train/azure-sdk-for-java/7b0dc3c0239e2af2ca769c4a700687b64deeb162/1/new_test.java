@Test(groups = "samples", timeOut = TIMEOUT)
    public void createDatabase_Async() throws Exception {
        Flux<ResourceResponse<Database>> createDatabaseObservable = client.createDatabase(getDatabaseDefinition(),
                                                                                                     null);

        final CountDownLatch completionLatch = new CountDownLatch(1);

        createDatabaseObservable.single() // We know there is only single result
                .subscribe(databaseResourceResponse -> {
                    System.out.println(databaseResourceResponse.getActivityId());
                    completionLatch.countDown();
                }, error -> {
                    System.err.println(
                            "an error occurred while creating the database: actual cause: " + error.getMessage());
                    completionLatch.countDown();
                });

        // Wait till database creation completes
        completionLatch.await();
    }