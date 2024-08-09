@Test
    public void testSendMessages() {
        List<ProtocolMessage> protocolMessages = new LinkedList<>();
        protocolMessages.add(message);
        List<Record> records = new LinkedList<>();
        records.add(record);
        executor.sendMessages(protocolMessages, records);
        byte[] sendByte = ((FakeTransportHandler) context.getTransportHandler()).getSendByte();
        LOGGER.info(ArrayConverter.bytesToHexString(sendByte));
        assertArrayEquals(new byte[] { 21, 03, 03, 00, 02, 02, 51 }, sendByte);
    }