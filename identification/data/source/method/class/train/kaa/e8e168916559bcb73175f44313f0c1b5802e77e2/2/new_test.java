@Test
    public void testGetProfileFilterRecord() throws Exception {
        ProfileFilterDto profileFilter = createProfileFilter();

        ProfileFilterRecordDto profileFilterRecord = client.getProfileFilterRecord(profileFilter.getEndpointProfileSchemaId(),
                profileFilter.getServerProfileSchemaId(), profileFilter.getEndpointGroupId());

        Assert.assertNotNull(profileFilterRecord);
        Assert.assertNotNull(profileFilterRecord.getInactiveStructureDto());
        assertProfileFiltersEquals(profileFilter, profileFilterRecord.getInactiveStructureDto());
    }