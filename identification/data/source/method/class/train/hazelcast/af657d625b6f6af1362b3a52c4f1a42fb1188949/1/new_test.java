    @Test
    public void getMapConfig_randomMember() throws Exception {
        MCMapConfig mapConfig = resolve(managementCenterService.getMapConfig("map-1"));
        assertEquals(1, mapConfig.getBackupCount());
    }