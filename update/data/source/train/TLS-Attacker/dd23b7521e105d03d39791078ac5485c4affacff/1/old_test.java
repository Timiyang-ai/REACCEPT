@Test
    public void testGetNextProtocolMessageFromPeer() {
	WorkflowConfigurationFactory factory = WorkflowConfigurationFactory.createInstance(new ClientCommandConfig());
	TlsContext context = factory.createFullTlsContext();
	context.setMyConnectionEnd(ConnectionEnd.CLIENT);

	ProtocolMessage pm = TlsContextAnalyzer.getNextProtocolMessageFromPeer(context, 1);
	assertEquals(ProtocolMessageType.HANDSHAKE, pm.getProtocolMessageType());

	pm = TlsContextAnalyzer.getNextProtocolMessageFromPeer(context, 5);
	assertEquals(ProtocolMessageType.CHANGE_CIPHER_SPEC, pm.getProtocolMessageType());
    }