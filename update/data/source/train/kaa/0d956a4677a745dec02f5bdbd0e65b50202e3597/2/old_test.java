@Test
    public void testGetProfileSchema() throws Exception {
        ProfileSchemaDto profileSchema = createProfileSchema();
        
        ProfileSchemaDto storedProfileSchema = client.getProfileSchema(profileSchema.getId());
        
        Assert.assertNotNull(storedProfileSchema);
        assertProfileSchemasEquals(profileSchema, storedProfileSchema);
    }