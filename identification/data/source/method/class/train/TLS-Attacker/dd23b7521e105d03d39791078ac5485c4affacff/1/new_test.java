@Test
    public void testGetNextProtocolMessageFromPeer() {
	WorkflowConfigurationFactory factory = WorkflowConfigurationFactory.createInstance(new ClientCommandConfig());
	TlsContext context = factory.createFullServerResponseTlsContext(ConnectionEnd.CLIENT);
	context.setMyConnectionEnd(ConnectionEnd.CLIENT);

	ProtocolMessage pm = TlsContextAnalyzer.getNextReceiveProtocolMessage(context, 1);
	assertEquals(ProtocolMessageType.HANDSHAKE, pm.getProtocolMessageType());

	pm = TlsContextAnalyzer.getNextReceiveProtocolMessage(context, 3);
	assertEquals(ProtocolMessageType.CHANGE_CIPHER_SPEC, pm.getProtocolMessageType());
    }