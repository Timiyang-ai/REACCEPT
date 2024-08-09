public static MongoCredential getMongoChallengeCredentials(String ApplicationRoot, String path)
    {
        //Some over paranoid input validation never hurts.
        path = path.replaceAll("\\.", "").replaceAll("/", "");
        log.debug("Path = " + path);

        String props;
        MongoCredential credential;

        props = new File(Database.class.getResource("/challenges/" + path + ".properties").getFile()).getAbsolutePath();
        log.debug("Level Properties File = " + path + ".properties");

        String username = FileInputProperties.readfile(props, "databaseUsername");
        log.debug("Connecting to DB with: " + username);
        String password = FileInputProperties.readfile(props, "databasePassword");
        String dbname = FileInputProperties.readfile(props, "databaseName");
        String dbCollectionName = FileInputProperties.readfile(props, "databaseCollection");
        log.debug("Mongo db & collection = " + dbname + " " + dbCollectionName);

        credential = MongoCredential.createScramSha1Credential(username, dbname, password.toCharArray());

        return credential;
    }