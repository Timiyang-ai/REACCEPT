@Test
    public void testAddEventClassFamilyVersion() throws Exception {
        tenantAdminDto = createTenant(tenantAdminUser);
        loginTenantAdmin(tenantAdminUser);
        EventClassFamilyDto eventClassFamily = createEventClassFamily(tenantAdminDto.getId());
        EventClassFamilyVersionDto eventClassFamilyVersion = createEventClassFamilyVersion(eventClassFamily.getId());
        client.addEventClassFamilyVersion(eventClassFamily.getId(), eventClassFamilyVersion);
        List<EventClassFamilyVersionDto> schemas = eventClassService.findEventClassFamilyVersionsById(eventClassFamily.getId());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(1, schemas.size());
        EventClassFamilyVersionDto eventSchema = schemas.get(0);
        Assert.assertNotNull(eventSchema);
        Assert.assertEquals(1, eventSchema.getVersion());

        client.addEventClassFamilyVersion(eventClassFamily.getId(), eventClassFamilyVersion);
        schemas = eventClassService.findEventClassFamilyVersionsById(eventClassFamily.getId());
        Assert.assertNotNull(schemas);
        Assert.assertEquals(2, schemas.size());
        eventSchema = schemas.get(1);
        Assert.assertNotNull(eventSchema);
        Assert.assertEquals(2, eventSchema.getVersion());
    }