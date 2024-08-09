@Test
    public void testGetSchemaID() 
    {
        assertThat("testGetSchemaID 0",ms.getSchemaID(), equalTo(MetadataSchema.DC_SCHEMA_ID));
    }