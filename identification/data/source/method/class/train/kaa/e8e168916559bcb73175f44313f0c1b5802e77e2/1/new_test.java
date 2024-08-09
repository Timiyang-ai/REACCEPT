@Test
    public void testDeleteProfileFilterRecord() throws Exception {
        EndpointGroupDto endpointGroup = createEndpointGroup();

        ProfileFilterDto profileFilter1 = createProfileFilter(null, null, endpointGroup.getId());
        ProfileFilterDto profileFilter2 = createProfileFilter(null, null, endpointGroup.getId());

        client.activateProfileFilter(profileFilter2.getId());

        client.deleteProfileFilterRecord(profileFilter2.getEndpointProfileSchemaId(), profileFilter2.getServerProfileSchemaId(), endpointGroup.getId());

        List<ProfileFilterRecordDto> profileFilterRecords = client.getProfileFilterRecords(endpointGroup.getId(), false);

        Assert.assertNotNull(profileFilterRecords);
        Assert.assertEquals(1, profileFilterRecords.size());
        assertProfileFiltersEquals(profileFilter1, profileFilterRecords.get(0).getInactiveStructureDto());

        client.deleteProfileFilterRecord(profileFilter1.getEndpointProfileSchemaId(), profileFilter1.getServerProfileSchemaId(), endpointGroup.getId());
        profileFilterRecords = client.getProfileFilterRecords(endpointGroup.getId(), false);
        Assert.assertNotNull(profileFilterRecords);
        Assert.assertEquals(0, profileFilterRecords.size());
    }