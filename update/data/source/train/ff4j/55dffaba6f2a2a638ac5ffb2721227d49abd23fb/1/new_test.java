@Test
    public void testPostisFlippedInvalidParameter() {
        // Given
        assertFF4J.assertThatFeatureExist(AWESOME);
        // When
        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("InvalidParameter", "localhost");
        ClientResponse resHttp = resourceff4j().path(OPERATION_CHECK) //
                .path(AWESOME) //
                .type(MediaType.APPLICATION_FORM_URLENCODED).//
                post(ClientResponse.class, formData);
        String resEntity = resHttp.getEntity(String.class);
        // Then
        Assert.assertEquals("Expected status is 400", Status.BAD_REQUEST.getStatusCode(), resHttp.getStatus());
        Assert.assertNotNull(resEntity);
        Assert.assertTrue(resEntity.contains("Invalid parameter"));
    }