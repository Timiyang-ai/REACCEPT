public boolean isSecure() {
		return (correlationContext != null && correlationContext.get(DtlsCorrelationContext.KEY_SESSION_ID) != null);
	}