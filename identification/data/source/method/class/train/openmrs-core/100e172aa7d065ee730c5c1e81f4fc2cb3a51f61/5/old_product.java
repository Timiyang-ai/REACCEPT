public static boolean updatesRequired() throws Exception {
		log.debug("checking for updates");
		
		Database database = null;
		try {
			
			Liquibase liquibase = getLiquibase(null, null);
			database = liquibase.getDatabase();
			
			// if the db is locked, it means there was a crash
			// or someone is executing db updates right now. either way
			// returning true here stops the openmrs startup and shows
			// the user the maintenance wizard for updates
			if (isLocked()) {
				return true;
			}
		}
		catch (Exception e) {
			// do nothing
		}
		finally {
			try {
				database.getConnection().close();
			}
			catch (Throwable t) {
				// pass
			}
		}
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges();
		return changesets.size() > 0;
	}