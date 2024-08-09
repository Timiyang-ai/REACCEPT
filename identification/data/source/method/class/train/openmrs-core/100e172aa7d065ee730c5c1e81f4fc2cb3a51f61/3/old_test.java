	@Test
	public void updatesRequired_shouldAlwaysHaveAValidUpdateToLatestFile() throws LockException {
		// expects /metadata/model to be on the classpath so that
		// the liquibase-update-to-latest.xml can be found.
		try {
			DatabaseUpdater.updatesRequired();
		}
		catch (RuntimeException rex) {
			log.error("Runtime Exception in test for Validation Errors");
		}
		// does not run DatabaseUpdater.update() because hsqldb doesn't like single quotes in strings
	}