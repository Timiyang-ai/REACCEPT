protected StartupErrorFilterModel getModel() {
		// this object was initialized in the #init(FilterConfig) method
		return new StartupErrorFilterModel(Listener.getErrorAtStartup());
	}