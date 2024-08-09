@Test
    public void testGet() {
        // Given
        Assert.assertEquals(InMemoryFeatureStore.class, ff4j.getFeatureStore().getClass());
        // When
        ClientResponse resHttp = resourceff4j().type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        
        // Then, HTTPResponse
        Assert.assertEquals("Expected status is 200", Status.OK.getStatusCode(), resHttp.getStatus());
        
        // Then, Entity Object
        //Assert.assertTrue(resEntity.contains("uptime"));
    }