@Test
    public void testCreateBatch() {
        // Expected Mock response
        String batchId = UUID.randomUUID().toString();
        String name = "testBatch";
        String currentTime = DateTime.now().toString();
        List<Property> propertyList = Arrays.asList(
                new Property("media_type", MediaType.TEXT_HTML), new Property("num_docs", "2"));
        Batch response = createMockBatch(batchId, name, currentTime, currentTime, propertyList);

        mockServer.when(
                request().withMethod("POST").withPath(DocumentConversion.BATCHES_PATH)
        ).respond((response().withBody(GsonSingleton.getGson().toJson(response))));

        // Call create batch
        Batch batch = service.createBatch(name, propertyList);
        Assert.assertNotNull(batch);
        Assert.assertEquals(batch.toString(), response.toString());
    }