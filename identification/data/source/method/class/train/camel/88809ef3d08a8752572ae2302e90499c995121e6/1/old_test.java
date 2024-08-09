@Test
    public void testDestroyObject() throws Exception {
        ConnectionFactoryResource pool = new ConnectionFactoryResource(1, connectionFactory);
        pool.fillPool();
        assertNotNull(pool);
        ActiveMQConnection connection = (ActiveMQConnection)pool.borrowObject();
        assertNotNull(connection);
        assertTrue(connection.isStarted());
        pool.drainPool();
        assertTrue(pool.size() == 0);
    }