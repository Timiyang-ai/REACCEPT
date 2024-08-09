    @Test
    public void pollMCEvents() throws Exception {
        List<MCEventDTO> events = resolve(managementCenterService.pollMCEvents(members[2]));
        assertEquals(0, events.size());

        getMemberMCService(hazelcastInstances[2]).log(new TestEvent());
        getMemberMCService(hazelcastInstances[2]).log(new TestEvent());

        events = resolve(managementCenterService.pollMCEvents(members[2]));
        assertEquals(2, events.size());
        assertEquals(42, events.get(0).getTimestamp());
        assertEquals(WAN_SYNC_STARTED.getCode(), events.get(0).getType());
        assertEquals(new TestEvent().toJson().toString(), events.get(0).getDataJson());
    }