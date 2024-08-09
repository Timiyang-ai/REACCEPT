@Test
    public void testAddMetaData() throws Exception {
        result1 = OspfUtil.addMetadata(2, packet, 1, Ip4Address.valueOf("2.2.2.2"));
        assertThat(result1, is(notNullValue()));
    }