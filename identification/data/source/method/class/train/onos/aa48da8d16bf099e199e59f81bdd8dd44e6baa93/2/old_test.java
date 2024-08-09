@Test(expected = Exception.class)
    public void testGetLsaBodyAsByteArray() throws Exception {
        networkLsa.addAttachedRouter(Ip4Address.valueOf("1.1.1.1"));
        networkLsa.addAttachedRouter(Ip4Address.valueOf("2.2.2.2"));
        networkLsa.addAttachedRouter(Ip4Address.valueOf("3.3.3.3"));
        result1 = networkLsa.getLSABodyAsByteArray();
        assertThat(result1, is(notNullValue()));
    }