public static String getMongoChallengeCollName(String ApplicationRoot, String path){
        //Some over paranoid input validation never hurts.
        path = path.replaceAll("\\.", "").replaceAll("/", "");
        log.debug("Path = " + path);

        String props;

        props = new File(Database.class.getResource("/challenges/" + path + ".properties").getFile()).getAbsolutePath();

        String dbCollectionName = FileInputProperties.readfile(props, "databaseCollection");

        return  dbCollectionName;

    }