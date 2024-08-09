public static String getMongoChallengeCollName(String ApplicationRoot, String path){

        String props;
        //Some over paranoid input validation never hurts.
        path = path.replaceAll("\\.", "").replaceAll("/", "");
        log.debug("Path = " + path);

        props = new File(Database.class.getResource("/challenges/" + path + ".properties").getFile()).getAbsolutePath();

        log.debug("Properties File: " + props);
        String dbCollectionName = FileInputProperties.readfile(props, "databaseCollection");

        return  dbCollectionName;

    }