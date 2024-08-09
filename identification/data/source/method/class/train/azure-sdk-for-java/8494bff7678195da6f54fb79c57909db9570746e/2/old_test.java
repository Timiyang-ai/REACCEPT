@Test(groups = "samples", timeOut = TIMEOUT)
    public void createDocument_toBlocking() {
        Document doc = new Document(String.format("{ 'id': 'doc%s', 'counter': '%d'}", UUID.randomUUID().toString(), 1));
        Flux<ResourceResponse<Document>> createDocumentObservable = client
                .createDocument(getCollectionLink(), doc, null, true);

        // toBlocking() converts to a blocking observable.
        // single() gets the only result.
        createDocumentObservable.single().block();
    }