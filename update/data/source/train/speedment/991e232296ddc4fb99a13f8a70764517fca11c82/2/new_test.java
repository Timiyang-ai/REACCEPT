@Test
    public void testGetConnection() throws Exception {
        String uri = "thecooldatabase";
        String user = "tryggve";
        char[] password = "arne".toCharArray();
        final PoolableConnection result = instance.getConnection(uri, user, password);
        assertNotNull(result);
    }