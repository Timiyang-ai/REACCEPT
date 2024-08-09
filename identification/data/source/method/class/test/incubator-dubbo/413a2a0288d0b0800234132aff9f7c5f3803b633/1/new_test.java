@Test
    public void testDestroy() {
        MulticastSocket socket = registry.getMulticastSocket();
        assertFalse(socket.isClosed());

        // then destroy, the multicast socket will be closed
        registry.destroy();
        socket = registry.getMulticastSocket();
        assertTrue(socket.isClosed());
    }