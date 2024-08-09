@Test(groups = { "direct" }, timeOut = TIMEOUT)
    public void create() {
        final Document docDefinition = getDocumentDefinition();

        Observable<ResourceResponse<Document>> createObservable = client.createDocument(
            this.getCollectionLink(), docDefinition, null, false);

        ResourceResponseValidator<Document> validator = new ResourceResponseValidator.Builder<Document>()
            .withId(docDefinition.id())
            .build();

        validateSuccess(createObservable, validator, TIMEOUT);
        validateNoDocumentOperationThroughGateway();
    }