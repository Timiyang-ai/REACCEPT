public static Module startModule(Module module, boolean isOpenmrsStartup,
	        AbstractRefreshableApplicationContext applicationContext) throws ModuleException {
		return Daemon.startModule(module, isOpenmrsStartup, applicationContext);
	}