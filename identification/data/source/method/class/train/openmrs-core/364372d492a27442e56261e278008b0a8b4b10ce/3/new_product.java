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
		
		this.startupErrorMessage = sb.toString();
	}