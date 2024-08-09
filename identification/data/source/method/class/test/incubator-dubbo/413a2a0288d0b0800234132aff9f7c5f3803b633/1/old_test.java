@Test
    public void testDestroy() {
        MulticastSocket socket = registry.getMutilcastSocket();
        assertFalse(socket.isClosed());

        // then destroy, the multicast socket will be closed
        registry.destroy();
        socket = registry.getMutilcastSocket();
        assertTrue(socket.isClosed());
    }