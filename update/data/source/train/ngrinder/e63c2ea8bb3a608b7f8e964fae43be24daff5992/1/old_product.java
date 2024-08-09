@PostConstruct
	public void init() {
		try {
			CoreLogger.LOGGER.info("NGrinder is starting...");
			home = resolveHome();
			exHome = resolveExHome();
			copyDefaultConfigurationFiles();
			loadInternalProperties();
			loadSystemProperties();
			initHomeMonitor();
			// Load cluster in advance. cluster mode is not dynamically
			// reloadable.
			cluster = getSystemProperties().getPropertyBoolean(NGrinderConstants.NGRINDER_PROP_CLUSTER_MODE, false);
			initLogger(isTestMode());
			resolveLocalIp();
			loadDatabaseProperties();
			versionString = getVersion();
		} catch (IOException e) {
			throw new ConfigurationException("Error while init nGrinder", e);
		}
	}