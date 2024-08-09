@Test
    public void testReceiveString() throws Exception {
        transportHandler.setFetchableByte(ArrayConverter.concatenate(new byte[] { 0x17, 0x03, 0x03, 0x00, 0x04 },
                "test".getBytes()));
        String receivedString = socket.receiveString();
        assertEquals("test", receivedString);
    }