@Test
    public void testVRouterId() throws Exception {
        assertThat(config.vRouterId(), is(VROUTER_ID_1));
    }