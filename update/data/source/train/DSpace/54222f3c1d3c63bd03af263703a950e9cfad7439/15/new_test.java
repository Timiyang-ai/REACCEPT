@Test
    public void testGetSchemaID() throws SQLException
    {
        assertThat("testGetSchemaID 0",ms.getSchemaID(), equalTo(metadataSchemaService.find(context, MetadataSchema.DC_SCHEMA).getSchemaID()));
    }