@Test(groups = { "direct" }, timeOut = TIMEOUT)
    public void read() throws Exception {
        Document docDefinition = this.getDocumentDefinition();
        Document document = client.createDocument(getCollectionLink(), docDefinition, null, false).toBlocking().single().getResource();

        // give times to replicas to catch up after a write
        waitIfNeededForReplicasToCatchUp(clientBuilder());

        String pkValue = document.getString(PARTITION_KEY_FIELD_NAME);

        RequestOptions options = new RequestOptions();
        options.setPartitionKey(new PartitionKey(pkValue));

        String docLink =
                String.format("dbs/%s/colls/%s/docs/%s", createdDatabase.id(), createdCollection.id(), document.id());

        ResourceResponseValidator<Document> validator = new ResourceResponseValidator.Builder<Document>()
                .withId(docDefinition.id())
                .build();

        validateSuccess(client.readDocument(docLink, options), validator, TIMEOUT);

        validateNoDocumentOperationThroughGateway();
    }