@Test
    public void testSendLsa() throws Exception {
        channel = EasyMock.createMock(Channel.class);
        ospfNbr.sendLsa(lsaHeader, Ip4Address.valueOf("1.1.1.1"), channel);
        assertThat(ospfNbr, is(notNullValue()));
    }