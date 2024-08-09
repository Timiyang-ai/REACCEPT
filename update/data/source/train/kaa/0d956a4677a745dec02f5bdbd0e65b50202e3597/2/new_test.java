@Test
    public void testGetProfileSchema() throws Exception {
        EndpointProfileSchemaDto profileSchema = createProfileSchema();
        
        EndpointProfileSchemaDto storedProfileSchema = client.getProfileSchema(profileSchema.getId());
        
        Assert.assertNotNull(storedProfileSchema);
        assertProfileSchemasEquals(profileSchema, storedProfileSchema);
    }