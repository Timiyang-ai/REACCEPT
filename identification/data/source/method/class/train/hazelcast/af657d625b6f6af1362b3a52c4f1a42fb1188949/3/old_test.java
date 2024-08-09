    @Test
    public void updateMapConfig() throws Exception {
        hazelcastInstances[0].getMap("map-1").put(1, 1);

        UpdateMapConfigParameters parameters = new UpdateMapConfigParameters(
                "map-1", 27, 29, EvictionPolicy.LRU, false, 35, PER_NODE);
        resolve(managementCenterService.updateMapConfig(members[0], parameters));

        MCMapConfig retrievedConfig1 = resolve(managementCenterService.getMapConfig(members[0], "map-1"));
        assertEquals(27, retrievedConfig1.getTimeToLiveSeconds());
        assertEquals(29, retrievedConfig1.getMaxIdleSeconds());
        assertEquals(35, retrievedConfig1.getMaxSize());
        assertEquals(PER_NODE, retrievedConfig1.getMaxSizePolicy());

        MCMapConfig retrievedConfig2 = resolve(managementCenterService.getMapConfig(members[1], "map-1"));
        assertEquals(DEFAULT_TTL_SECONDS, retrievedConfig2.getTimeToLiveSeconds());
        assertEquals(DEFAULT_MAX_IDLE_SECONDS, retrievedConfig2.getMaxIdleSeconds());
        assertEquals(DEFAULT_MAX_SIZE, retrievedConfig2.getMaxSize());
    }