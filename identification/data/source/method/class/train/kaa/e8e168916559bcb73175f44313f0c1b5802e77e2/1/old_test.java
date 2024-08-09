@Test
    public void testDeleteProfileFilterRecord() throws Exception {
        EndpointGroupDto endpointGroup = createEndpointGroup();
        
        ProfileFilterDto profileFilter1 = createProfileFilter(null, endpointGroup.getId());
        ProfileFilterDto profileFilter2 = createProfileFilter(null, endpointGroup.getId());
        
        client.activateProfileFilter(profileFilter2.getId());
        
        client.deleteProfileFilterRecord(profileFilter2.getSchemaId(), endpointGroup.getId());
        
        List<ProfileFilterRecordDto> profileFilterRecords = client.getProfileFilterRecords(endpointGroup.getId(), false);
        
        Assert.assertNotNull(profileFilterRecords);
        Assert.assertEquals(1, profileFilterRecords.size());
        assertProfileFiltersEquals(profileFilter1, profileFilterRecords.get(0).getInactiveProfileFilter());
        
        client.deleteProfileFilterRecord(profileFilter1.getSchemaId(), endpointGroup.getId());
        profileFilterRecords = client.getProfileFilterRecords(endpointGroup.getId(), false);
        Assert.assertNotNull(profileFilterRecords);
        Assert.assertEquals(0, profileFilterRecords.size());
    }