public static boolean updatesRequired() throws Exception {
		log.debug("checking for updates");
		
		List<OpenMRSChangeSet> changesets = getUnrunDatabaseChanges();
		return changesets.size() > 0;
	}