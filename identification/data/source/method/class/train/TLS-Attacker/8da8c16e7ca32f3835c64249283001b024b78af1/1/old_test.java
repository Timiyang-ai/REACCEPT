@Test
    public void testParseMessageAction() {
        AlertHandler handler = new AlertHandler(new TlsContext());
        handler.setProtocolMessage(new AlertMessage());
        byte[] message = { 3, 3 };
        int pointer = handler.parseMessageAction(message, 0);
        assertEquals(2, pointer);
        assertEquals(3, handler.getProtocolMessage().getLevel().getValue().byteValue());
        assertEquals(3, handler.getProtocolMessage().getDescription().getValue().byteValue());
    }