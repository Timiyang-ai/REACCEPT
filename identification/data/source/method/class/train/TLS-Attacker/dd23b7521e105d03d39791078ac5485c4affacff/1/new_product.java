public static ProtocolMessage getNextReceiveProtocolMessage(TlsContext tlsContext, int position) {
	ConnectionEnd peer = tlsContext.getMyConnectionEnd().getPeer();
	for (int i = position; i < tlsContext.getWorkflowTrace().getConfiguredReceivingMessages().size(); i++) {
	    ProtocolMessage pm = tlsContext.getWorkflowTrace().getConfiguredReceivingMessages().get(i);
	    return pm;
	}
	return null;
    }