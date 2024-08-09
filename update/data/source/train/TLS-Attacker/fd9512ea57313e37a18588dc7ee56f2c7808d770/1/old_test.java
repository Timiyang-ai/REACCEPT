@Test
    public void testRecieveRawBytes() throws Exception {
        transportHandler.setFetchableByte(new byte[] { 1, 2, 3 });
        byte[] received = socket.recieveRawBytes();
        assertArrayEquals(new byte[] { 1, 2, 3 }, received);
    }