@Test
    public void testGetSchemaID() throws SQLException
    {
        assertThat("testGetSchemaID 0",ms.getID(), equalTo(metadataSchemaService.find(context, MetadataSchema.DC_SCHEMA).getID()));
    }