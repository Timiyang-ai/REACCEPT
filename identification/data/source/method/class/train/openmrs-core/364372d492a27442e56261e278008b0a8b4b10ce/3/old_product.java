public void setStartupErrorMessage(String exceptionMessage, Throwable t) {
		if (t == null)
			throw new ModuleException("Startup error value cannot be null", this.getModuleId());
		
		StringBuffer sb = new StringBuffer();
		
		// if exceptionMessage is not null, append it
		if (exceptionMessage != null) {
			sb.append(exceptionMessage);
			sb.append("\n");
		}
		
		sb.append(t.getMessage());
		sb.append("\n");
		
		// loop over and append all stacktrace elements marking the "openmrs" ones 
		for (StackTraceElement traceElement : t.getStackTrace()) {
			if (traceElement.getClassName().contains("openmrs"))
				sb.append(" ** ");
			sb.append(traceElement);
			sb.append("\n");
		}
		
		this.startupErrorMessage = sb.toString();
	}