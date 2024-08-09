public static boolean updatesRequired() throws Exception {
		log.debug("checking for updates");
		
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges(CHANGE_LOG_FILE);
		return changesets.size() > 0;
	}