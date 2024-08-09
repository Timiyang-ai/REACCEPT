    @Test
    public void matchMCConfig() throws Exception {
        ClientBwListDTO bwListDTO = new ClientBwListDTO(ClientBwListDTO.Mode.DISABLED, emptyList());
        getMemberMCService(hazelcastInstances[0]).applyMCConfig("testETag", bwListDTO);

        assertTrue(resolve(managementCenterService.matchMCConfig(members[0], "testETag")));
        assertFalse(resolve(managementCenterService.matchMCConfig(members[0], "wrongETag")));
    }