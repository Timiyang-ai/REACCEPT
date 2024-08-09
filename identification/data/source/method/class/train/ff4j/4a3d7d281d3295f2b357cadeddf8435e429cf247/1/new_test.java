@Test
    public void testGet() {
        // Given
        Assert.assertTrue(ff4j.getFeatureStore() instanceof InMemoryFeatureStore);
        // When
        ClientResponse resHttp = resourceStore().get(ClientResponse.class);
        String resEntity = resHttp.getEntity(String.class);
        // Then, HTTPResponse
        Assert.assertEquals("Expected status is 200", Status.OK.getStatusCode(), resHttp.getStatus());
        Assert.assertNotNull(resEntity);
        // Then, Entity Object
        Assert.assertTrue(resEntity.contains(InMemoryFeatureStore.class.getCanonicalName()));
    }