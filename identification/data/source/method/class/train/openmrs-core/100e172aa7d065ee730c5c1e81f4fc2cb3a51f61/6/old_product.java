public static boolean updatesRequired() throws Exception {
		log.debug("checking for updates");
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges();
		Database database = null;
		try {
			
			Liquibase liquibase = getLiquibase(null, null);
			database = liquibase.getDatabase();
			
			// if the db is locked, it means there was a crash
			// or someone is executing db updates right now. either way
			// returning true here stops the openmrs startup and shows
			// the user the maintenance wizard for updates
			if (isLocked()) {
				// if there is a db lock but there are no db changes we undo the
				// lock
				if (changesets.size() == 0) {
					DatabaseUpdater.releaseDatabaseLock();
					return false;
				} else {
					return true;
				}
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
		return changesets.size() > 0;
	}