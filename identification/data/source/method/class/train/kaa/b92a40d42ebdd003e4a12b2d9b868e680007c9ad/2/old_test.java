@Test
    public void testGetEventClassFamiliesByApplicationToken() throws Exception {
        ApplicationDto application = createApplication(tenantAdminDto);
        EventClassFamilyDto eventClassFamily = createEventClassFamily(application.getTenantId());
        createApplicationEventFamilyMap(application.getId(), eventClassFamily.getId(), 1);

        loginTenantDeveloper(tenantDeveloperUser);

        List<AefMapInfoDto> applicationEcfs = client.getEventClassFamiliesByApplicationToken(application.getApplicationToken());
        Assert.assertNotNull(applicationEcfs);
        Assert.assertEquals(1, applicationEcfs.size());
        Assert.assertNotNull(applicationEcfs.get(0));
        Assert.assertEquals(eventClassFamily.getId(), applicationEcfs.get(0).getEcfId());
        Assert.assertEquals(eventClassFamily.getName(), applicationEcfs.get(0).getEcfName());
        Assert.assertEquals(1, applicationEcfs.get(0).getVersion());
    }