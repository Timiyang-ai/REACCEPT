@Test(groups = "samples", timeOut = TIMEOUT)
    public void createCollection_toBlocking() {
        Mono<ResourceResponse<DocumentCollection>> createCollectionObservable = client
                .createCollection(getDatabaseLink(), collectionDefinition, null);

        // single() converts the flux to a mono.
        // block() gets the only result.
        createCollectionObservable.single().block();
    }