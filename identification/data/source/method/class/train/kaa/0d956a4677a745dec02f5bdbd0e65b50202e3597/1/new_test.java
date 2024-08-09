@Test
    public void testGetProfileSchemasByApplicationId() throws Exception {
        
        List<EndpointProfileSchemaDto> profileSchemas  = new ArrayList<>(11);
        ApplicationDto application = createApplication(tenantAdminDto);
        
        loginTenantDeveloper(tenantDeveloperDto.getUsername());
        
        CTLSchemaInfoDto ctlSchema = this.createCTLSchema(this.ctlRandomFieldType(), CTL_DEFAULT_NAMESPACE, 1, CTLSchemaScopeDto.TENANT, null, null, null);

        List<EndpointProfileSchemaDto> defaultProfileSchemas = client.getProfileSchemas(application.getId());
        profileSchemas.addAll(defaultProfileSchemas);

        for (int i=0;i<10;i++) {
            EndpointProfileSchemaDto profileSchema = createProfileSchema(application.getId(), ctlSchema.getId());
            profileSchemas.add(profileSchema);
        }
        
        Collections.sort(profileSchemas, new IdComparator());
        
        List<EndpointProfileSchemaDto> storedProfileSchemas = client.getProfileSchemas(application.getId());

        Collections.sort(storedProfileSchemas, new IdComparator());
        
        Assert.assertEquals(profileSchemas.size(), storedProfileSchemas.size());
        for (int i=0;i<profileSchemas.size();i++) {
            EndpointProfileSchemaDto profileSchema = profileSchemas.get(i);
            EndpointProfileSchemaDto storedProfileSchema = storedProfileSchemas.get(i);
            assertProfileSchemasEquals(profileSchema, storedProfileSchema);
        }
    }