@Test
    public void testGetConnection() throws Exception {
        String uri = "thecooldatabase";
        String user = "tryggve";
        String password = "arne";
        final PoolableConnection result = instance.getConnection(uri, user, password);
        assertNotNull(result);
    }