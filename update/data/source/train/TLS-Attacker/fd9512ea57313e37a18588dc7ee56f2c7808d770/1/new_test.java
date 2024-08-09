@Test
    public void testReceiveRawBytes() throws Exception {
        transportHandler.setFetchableByte(new byte[] { 1, 2, 3 });
        byte[] received = socket.receiveRawBytes();
        assertArrayEquals(new byte[] { 1, 2, 3 }, received);
    }