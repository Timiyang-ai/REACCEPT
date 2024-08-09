public static boolean updatesRequired() {
		log.debug("checking for updates");
		
		Liquibase liquibase = null;
		try {
			liquibase = getLiquibase(null);
			List<ChangeSet> changesets = liquibase.listUnrunChangeSets(CONTEXT);
			return changesets.size() > 0;
			
		}
		catch (Exception e) {
			throw new RuntimeException("error checking for updates in the database", e);
		}
		finally {
			try {
				liquibase.getDatabase().getConnection().close();
			}
			catch (Throwable t) {
				//pass
			}
		}
	}