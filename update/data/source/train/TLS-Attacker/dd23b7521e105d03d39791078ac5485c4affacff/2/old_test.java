@Test
    public void testContainsAlertAfterModifiedMessage() {
	WorkflowConfigurationFactory factory = WorkflowConfigurationFactory.createInstance(new ClientCommandConfig());
	TlsContext context = factory.createFullTlsContext();
	context.setMyConnectionEnd(ConnectionEnd.CLIENT);

	ApplicationMessage am = (ApplicationMessage) context.getWorkflowTrace().getFirstProtocolMessage(
		ProtocolMessageType.APPLICATION_DATA);
	ModifiableByteArray data = new ModifiableByteArray();
	data.setOriginalValue(new byte[0]);
	data.setModification(ByteArrayModificationFactory.explicitValue(new byte[] { 1 }));
	am.setData(data);

	assertEquals("There is no alert after modification.", TlsContextAnalyzer.AnalyzerResponse.NO_ALERT,
		TlsContextAnalyzer.containsAlertAfterModifiedMessage(context));

	context.getWorkflowTrace().getProtocolMessages().add(new AlertMessage(ConnectionEnd.SERVER));
	assertEquals("There is an alert after modification.", TlsContextAnalyzer.AnalyzerResponse.ALERT,
		TlsContextAnalyzer.containsAlertAfterModifiedMessage(context));
    }