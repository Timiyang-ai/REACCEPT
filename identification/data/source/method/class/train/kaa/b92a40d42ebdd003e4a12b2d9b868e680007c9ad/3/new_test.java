@Test
    public void testGetVacantEventClassFamiliesByApplicationToken() throws Exception {
        ApplicationDto application = createApplication(tenantAdminDto);
        EventClassFamilyDto eventClassFamily = createEventClassFamily(application.getTenantId());
        createApplicationEventFamilyMap(application.getApplicationToken(), eventClassFamily.getId(), 1);

        loginTenantDeveloper(tenantDeveloperUser);
        List<EcfInfoDto> vacantEcfs = client.getVacantEventClassFamiliesByApplicationToken(application.getApplicationToken());
        Assert.assertNotNull(vacantEcfs);
        Assert.assertEquals(0, vacantEcfs.size());

        loginTenantAdmin(tenantAdminUser);
        client.addEventClassFamilySchema(eventClassFamily.getId(), TEST_EVENT_CLASS_FAMILY_SCHEMA);

        loginTenantDeveloper(tenantDeveloperUser);
        vacantEcfs = client.getVacantEventClassFamiliesByApplicationToken(application.getApplicationToken());
        Assert.assertNotNull(vacantEcfs);
        Assert.assertEquals(1, vacantEcfs.size());
        Assert.assertNotNull(vacantEcfs.get(0));
        Assert.assertEquals(eventClassFamily.getId(), vacantEcfs.get(0).getEcfId());
        Assert.assertEquals(eventClassFamily.getName(), vacantEcfs.get(0).getEcfName());
        Assert.assertEquals(2, vacantEcfs.get(0).getVersion());
    }