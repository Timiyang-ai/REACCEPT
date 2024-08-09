@Test
    public void testCreateProfileSchema() throws Exception {
        ProfileSchemaDto profileSchema = createProfileSchema();
        Assert.assertFalse(strIsEmpty(profileSchema.getId()));
    }