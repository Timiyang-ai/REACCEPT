@Test
    public void testSendMessages() {
        List<ProtocolMessage> protocolMessages = new LinkedList<>();
        protocolMessages.add(message);
        executor.sendMessages(protocolMessages);
        byte[] sendByte = ((FakeTransportHandler) context.getTransportHandler()).getSendByte();
        LOGGER.info(ArrayConverter.bytesToHexString(sendByte));
        assertArrayEquals(new byte[] { 21, 03, 03, 00, 02, 02, 51 }, sendByte);
    }