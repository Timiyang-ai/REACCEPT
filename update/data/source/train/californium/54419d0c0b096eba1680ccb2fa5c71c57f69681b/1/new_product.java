public boolean isSecure() {
		return (peerEndpointContext != null && peerEndpointContext.get(DtlsEndpointContext.KEY_SESSION_ID) != null);
	}