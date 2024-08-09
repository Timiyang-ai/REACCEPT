public boolean isSecure() {
		return (endpointContext != null && endpointContext.get(DtlsEndpointContext.KEY_SESSION_ID) != null);
	}