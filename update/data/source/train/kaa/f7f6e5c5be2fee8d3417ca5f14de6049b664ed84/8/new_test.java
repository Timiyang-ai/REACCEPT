@Test
    public void testGetConfigurationSchemaVersionsByApplicationId() throws Exception {

        List<ConfigurationSchemaDto> configurationSchemas  = new ArrayList<ConfigurationSchemaDto>(11);
        ApplicationDto application = createApplication(tenantAdminDto);
        
        loginTenantDeveloper(tenantDeveloperDto.getUsername());

        List<ConfigurationSchemaDto> defaultConfigurationSchemas = client.getConfigurationSchemas(application.getId());
        configurationSchemas.addAll(defaultConfigurationSchemas);

        for (int i=0;i<10;i++) {
            ConfigurationSchemaDto configurationSchema = createConfigurationSchema(application.getId());
            configurationSchemas.add(configurationSchema);
        }

        Collections.sort(configurationSchemas, new IdComparator());

        SchemaVersions schemaVersions = client.getSchemaVersionsByApplicationId(application.getId());
        
        List<VersionDto> storedConfigurationSchemas = schemaVersions.getConfigurationSchemaVersions();

        Collections.sort(storedConfigurationSchemas, new IdComparator());

        Assert.assertEquals(configurationSchemas.size(), storedConfigurationSchemas.size());
        for (int i=0;i<configurationSchemas.size();i++) {
            ConfigurationSchemaDto configurationSchema = configurationSchemas.get(i);
            VersionDto storedConfigurationSchema = storedConfigurationSchemas.get(i);
            assertSchemasEquals(configurationSchema, storedConfigurationSchema);
        }
    }