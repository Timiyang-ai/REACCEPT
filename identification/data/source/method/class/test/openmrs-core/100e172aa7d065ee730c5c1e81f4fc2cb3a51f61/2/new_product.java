public static boolean updatesRequired(String... changeLogFilenames) throws Exception {
		log.debug("checking for updates");
		
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges(changeLogFilenames);
		return changesets.size() > 0;
	}