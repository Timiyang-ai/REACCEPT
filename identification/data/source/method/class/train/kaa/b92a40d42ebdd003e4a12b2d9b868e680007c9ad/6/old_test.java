@Test
    public void testGetApplicationEventFamilyMapsByApplicationToken() throws Exception {
        List<ApplicationEventFamilyMapDto> applicationEventFamilyMaps  = new ArrayList<>(10);
        ApplicationDto application = createApplication(tenantAdminDto);
        EventClassFamilyDto eventClassFamily = createEventClassFamily(application.getTenantId());
        for (int i=0;i<10;i++) {
            ApplicationEventFamilyMapDto applicationEventFamilyMap = createApplicationEventFamilyMap(application.getId(), eventClassFamily.getId(), (i+1));
            applicationEventFamilyMaps.add(applicationEventFamilyMap);
        }

        Collections.sort(applicationEventFamilyMaps, new IdComparator());

        loginTenantDeveloper(tenantDeveloperUser);

        List<ApplicationEventFamilyMapDto> storedApplicationEventFamilyMaps = client.getApplicationEventFamilyMapsByApplicationToken(
                application.getApplicationToken());

        Collections.sort(storedApplicationEventFamilyMaps, new IdComparator());

        Assert.assertEquals(applicationEventFamilyMaps, storedApplicationEventFamilyMaps);
    }