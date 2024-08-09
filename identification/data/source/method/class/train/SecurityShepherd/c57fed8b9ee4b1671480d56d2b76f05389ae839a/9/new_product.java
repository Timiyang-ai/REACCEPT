public static String getMongoChallengeCollName(String ApplicationRoot, String path){

        String props;
        //Some over paranoid input validation never hurts.
        path = path.replaceAll("\\.", "").replaceAll("/", "");
        log.debug("Path = " + path);

        props = new File(Database.class.getResource("/challenges/" + path + ".properties").getFile()).getAbsolutePath();

        log.debug("Properties File: " + props);
		String dbCollectionName;
		try {
			dbCollectionName = FileInputProperties.readfile(props, "databaseCollection");
		} catch (IOException | PropertyNotFoundException e) {
			throw new RuntimeException(e);
		}

		return dbCollectionName;

    }