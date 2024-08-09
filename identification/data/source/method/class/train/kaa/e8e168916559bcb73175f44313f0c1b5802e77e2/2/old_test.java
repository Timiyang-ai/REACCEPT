@Test
    public void testGetProfileFilterRecord() throws Exception {
        ProfileFilterDto profileFilter = createProfileFilter();
        
        ProfileFilterRecordDto profileFilterRecord = client.getProfileFilterRecord(profileFilter.getSchemaId(), profileFilter.getEndpointGroupId());
        
        Assert.assertNotNull(profileFilterRecord);
        Assert.assertNotNull(profileFilterRecord.getInactiveProfileFilter());
        assertProfileFiltersEquals(profileFilter, profileFilterRecord.getInactiveProfileFilter());
    }