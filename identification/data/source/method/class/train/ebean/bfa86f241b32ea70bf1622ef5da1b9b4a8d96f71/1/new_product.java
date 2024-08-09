public static void deregisterShutdownHook() {
		synchronized (servers) {
			try {
				Runtime.getRuntime().removeShutdownHook(shutdownHook);
			} catch (IllegalStateException ex) {
				if (!ex.getMessage().equals("Shutdown in progress")) {
					throw ex;
				}
			}
		}
	}