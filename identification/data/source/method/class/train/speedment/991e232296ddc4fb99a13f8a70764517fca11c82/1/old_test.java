@Test
    public void testNewConnection() throws Exception {
        String uri = "someurl";
        String user = "a";
        String password = "b";
        Connection result = instance.newConnection(uri, user, password);
        assertNotNull(result);
        assertFalse(result.isClosed());
    }