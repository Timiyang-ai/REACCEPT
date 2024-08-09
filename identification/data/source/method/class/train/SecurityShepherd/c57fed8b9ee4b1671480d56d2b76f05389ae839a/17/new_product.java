public static MongoCredential getMongoChallengeCredentials(String ApplicationRoot, String path) throws FileNotFoundException
    {
        //Some over paranoid input validation never hurts.
        path = path.replaceAll("\\.", "").replaceAll("/", "");
        log.debug("Path = " + path);

        String props;
        MongoCredential credential;

        props = new File(MongoDatabase.class.getResource("/challenges/" + path + ".properties").getFile()).getAbsolutePath();
        log.debug("Level Properties File = " + path + ".properties");

		String username = "";
		char[] password = null;
		String dbname = "";
		
		try {
			username = FileInputProperties.readfile(props, "databaseUsername");
			log.debug("Username for Mongo level: " + username);
			password = FileInputProperties.readfile(props, "databasePassword").toCharArray();
			log.debug("Password for Mongo level read");
			dbname = FileInputProperties.readfile(props, "databaseName");
			log.debug("Mongo database name: " + dbname);
		} catch (FileNotFoundException e) {
			// db props file doesn't exist
			throw e;
		} catch (IOException | PropertyNotFoundException e) {
			throw new RuntimeException(e);
		}

        credential = MongoCredential.createScramSha1Credential(username, dbname, password);

        return credential;
    }