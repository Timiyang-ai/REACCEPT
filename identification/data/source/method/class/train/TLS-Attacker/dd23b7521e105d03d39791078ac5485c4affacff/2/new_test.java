@Test
    public void testContainsAlertAfterModifiedMessage() {
	WorkflowConfigurationFactory factory = WorkflowConfigurationFactory.createInstance(new ClientCommandConfig());
	TlsContext context = factory.createFullServerResponseTlsContext(ConnectionEnd.CLIENT);
	context.setMyConnectionEnd(ConnectionEnd.CLIENT);

	ApplicationMessage am = (ApplicationMessage) context.getWorkflowTrace().getFirstProtocolMessage(
		ProtocolMessageType.APPLICATION_DATA);
	ModifiableByteArray data = new ModifiableByteArray();
	data.setOriginalValue(new byte[0]);
	data.setModification(ByteArrayModificationFactory.explicitValue(new byte[] { 1 }));
	am.setData(data);

	assertEquals("There is no alert after modification.", TlsContextAnalyzer.AnalyzerResponse.NO_ALERT,
		TlsContextAnalyzer.containsAlertAfterModifiedMessage(context));
	ReceiveAction action = context.getWorkflowTrace().getReceiveActions().get(2);
	List<ProtocolMessage> messages = new LinkedList<>();
	messages.add(new AlertMessage());
	action.setConfiguredMessages(messages);
	context.getWorkflowTrace().add(new ReceiveAction(new AlertMessage()));
	// assertEquals("There is an alert after modification.",
	// TlsContextAnalyzer.AnalyzerResponse.ALERT,
	// TODO TlsContextAnalyzer.containsAlertAfterModifiedMessage(context));
    }