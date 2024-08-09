@Test
    public void testGet_readGroup() {
        // Given
        assertFF4J.assertThatGroupExist(G1);
        assertFF4J.assertThatGroupHasSize(2, G1);
        assertFF4J.assertThatFeatureIsInGroup(F3, G1);
        assertFF4J.assertThatFeatureIsInGroup(F4, G1);
        // When
        WebResource wrsc = resourceGroups().path(G1);
        ClientResponse resHttp = wrsc.get(ClientResponse.class);
        String resEntity = resHttp.getEntity(String.class);
        // Then, HTTPResponse
        Assert.assertEquals("Expected status is 200", Status.OK.getStatusCode(), resHttp.getStatus());
        // Then, Entity Object
        Assert.assertNotNull(resEntity);
        Feature[] f = parseFeatureArray(resEntity);
        Set<String> features = new HashSet<String>();
        for (Feature feature : f) {
            features.add(feature.getUid());
        }
        Assert.assertEquals(2, features.size());
        Assert.assertTrue(features.contains(F3));
        Assert.assertTrue(features.contains(F4));
    }