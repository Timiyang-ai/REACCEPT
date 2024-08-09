public static ProtocolMessage getNextProtocolMessageFromPeer(TlsContext tlsContext, int position) {
	ConnectionEnd peer = tlsContext.getMyConnectionEnd().getPeer();
	for (int i = position; i < tlsContext.getWorkflowTrace().getProtocolMessages().size(); i++) {
	    ProtocolMessage pm = tlsContext.getWorkflowTrace().getProtocolMessages().get(i);
	    if (peer == pm.getMessageIssuer()) {
		return pm;
	    }
	}
	return null;
    }