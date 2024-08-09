public static AnalyzerResponse containsAlertAfterModifiedMessage(TlsContext tlsContext) {
	int position = getModifiedMessagePosition(tlsContext);
	if (position == -1) {
	    return AnalyzerResponse.NO_MODIFICATION;
	} else {
	    ProtocolMessage pm = getNextReceiveProtocolMessage(tlsContext, position);
	    if (pm != null && pm.getProtocolMessageType() == ProtocolMessageType.ALERT) {
		return AnalyzerResponse.ALERT;
	    } else {
		return AnalyzerResponse.NO_ALERT;
	    }
	}
    }