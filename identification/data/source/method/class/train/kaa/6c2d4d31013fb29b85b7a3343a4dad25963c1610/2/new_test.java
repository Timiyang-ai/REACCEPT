@Test
    public void testGetEventClassesByFamilyIdVersionAndType() throws Exception {
        tenantAdminDto = createTenant(tenantAdminUser);
        loginTenantAdmin(tenantAdminUser);
        EventClassFamilyDto eventClassFamily = createEventClassFamily(tenantAdminDto.getId());
        EventClassFamilyVersionDto eventClassFamilyVersion = createEventClassFamilyVersion(eventClassFamily.getId());
        client.addEventClassFamilyVersion(eventClassFamily.getId(), eventClassFamilyVersion);
        List<EventClassDto> eventClasses = client.getEventClassesByFamilyIdVersionAndType(eventClassFamily.getId(), 1, EventClassType.EVENT);
        Assert.assertNotNull(eventClasses);
        Assert.assertEquals(1, eventClasses.size());
        for (EventClassDto eventClass : eventClasses) {
            Assert.assertEquals(eventClassFamily.getId(), eventClass.getEcfId());
            Assert.assertEquals(0, eventClass.getVersion());
        }
    }