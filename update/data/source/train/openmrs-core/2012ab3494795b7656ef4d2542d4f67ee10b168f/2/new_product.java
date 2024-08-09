public static boolean updatesRequired() throws LockException {
		log.debug("checking for updates");
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges();
		
		// if the db is locked, it means there was a crash
		// or someone is executing db updates right now. either way
		// returning true here stops the openmrs startup and shows
		// the user the maintenance wizard for updates
		if (isLocked() && changesets.isEmpty()) {
			// if there is a db lock but there are no db changes we undo the
			// lock
			DatabaseUpdater.releaseDatabaseLock();
			log.debug("db lock found and released automatically");
			return false;
		}
		
		return !changesets.isEmpty();
	}