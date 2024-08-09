@Test
    public void testGetProfileSchemaVersionsByApplicationId() throws Exception {
        
        List<ProfileSchemaDto> profileSchemas  = new ArrayList<>(11);
        ApplicationDto application = createApplication(tenantAdminDto);
        
        loginTenantDeveloper(tenantDeveloperDto.getUsername());
        
        List<ProfileSchemaDto> defaultProfileSchemas = client.getProfileSchemas(application.getId());
        profileSchemas.addAll(defaultProfileSchemas);

        for (int i=0;i<10;i++) {
            ProfileSchemaDto profileSchema = createProfileSchema(application.getId());
            profileSchemas.add(profileSchema);
        }
        
        Collections.sort(profileSchemas, new IdComparator());
        
        SchemaVersions schemaVersions = client.getSchemaVersionsByApplicationId(application.getId());
        
        List<SchemaDto> storedProfileSchemas = schemaVersions.getProfileSchemaVersions();

        Collections.sort(storedProfileSchemas, new IdComparator());
        
        Assert.assertEquals(profileSchemas.size(), storedProfileSchemas.size());
        for (int i=0;i<profileSchemas.size();i++) {
            ProfileSchemaDto profileSchema = profileSchemas.get(i);
            SchemaDto storedProfileSchema = storedProfileSchemas.get(i);
            assertSchemasEquals(profileSchema, storedProfileSchema);
        }
    }