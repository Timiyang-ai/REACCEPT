@Test
    public void testParseMessageAction() {
	byte[] serverHelloDoneMsg = { 0x0e, 0x00, 0x00, 0x00 };
	handler.initializeProtocolMessage();

	int endPointer = handler.parseMessage(serverHelloDoneMsg, 0);
	ServerHelloDoneMessage message = handler.getProtocolMessage();
	HandshakeMessageFields handshakeMessageFields = message.getMessageFields();

	assertNotNull("Confirm that parseMessage didn't return 'NULL'", endPointer);
	assertEquals("Confirm expected message type: \"ServerHelloDone\"", HandshakeMessageType.SERVER_HELLO_DONE,
		message.getHandshakeMessageType());
	assertEquals("Confirm expected message length of \"0\"", new Integer(0), handshakeMessageFields.getLength()
		.getValue());
	assertEquals("Confirm the correct value of endPointer representing the " + "actual number of message bytes",
		serverHelloDoneMsg.length, endPointer);
    }