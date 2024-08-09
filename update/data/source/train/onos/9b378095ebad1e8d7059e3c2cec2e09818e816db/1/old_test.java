@Test
    public void testSetupConnectivity() {
        Bandwidth bandwidth = Bandwidth.bps(100);
        Duration latency = Duration.ofMillis(10);

        OpticalConnectivityId cid = target.setupConnectivity(CP12, CP71, bandwidth, latency);
        assertNotNull(cid);

        // Checks path computation is called as expected
        assertEquals(1, pathService.edges.size());
        assertEquals(CP12.deviceId(), pathService.edges.get(0).getKey());
        assertEquals(CP71.deviceId(), pathService.edges.get(0).getValue());

        // Checks intents are installed as expected
        assertEquals(1, intentService.submitted.size());
        assertEquals(OpticalConnectivityIntent.class, intentService.submitted.get(0).getClass());
        OpticalConnectivityIntent connIntent = (OpticalConnectivityIntent) intentService.submitted.get(0);
        assertEquals(CP31, connIntent.getSrc());
        assertEquals(CP52, connIntent.getDst());
    }