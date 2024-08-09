@Test
    public void getLogSchemaVersionsByApplicationIdTest() throws Exception {
        LogSchemaDto logSchemaDto = createLogSchema();
        SchemaVersions schemaVersions = client.getSchemaVersionsByApplicationId(logSchemaDto.getApplicationId());
        List<VersionDto> found = schemaVersions.getLogSchemaVersions();
        Assert.assertEquals(2, found.size());
    }