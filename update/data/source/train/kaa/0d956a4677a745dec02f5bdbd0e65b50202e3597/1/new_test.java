@Test
    public void testCreateProfileSchema() throws Exception {
        EndpointProfileSchemaDto profileSchema = createProfileSchema();
        Assert.assertFalse(strIsEmpty(profileSchema.getId()));
    }