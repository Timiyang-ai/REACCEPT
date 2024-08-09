@Test
    public void testVRouterId() throws Exception {
        Optional<DeviceId> vRouterId = config.vRouterId();
        assertTrue(vRouterId.isPresent());
        assertThat(vRouterId.get(), is(VROUTER_ID_1));
    }