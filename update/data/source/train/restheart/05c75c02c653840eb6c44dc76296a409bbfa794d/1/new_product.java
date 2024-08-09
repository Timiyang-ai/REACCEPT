protected static void putFilename(
            final String formDataFilename,
            final String defaultFilename,
            final BsonDocument target) {
        // a filename attribute in optional properties overrides the provided part's filename 
        String filename = target.containsKey(FILENAME)
                && target.get(FILENAME).isString()
                ? target.get(FILENAME).asString().getValue()
                : formDataFilename;

        if (filename == null || filename.isEmpty()) {
            LOGGER.debug("No filename in neither multipart content disposition "
                    + "header nor in properties! Using default value");
            filename = defaultFilename;
        }

        target.append(FILENAME, new BsonString(filename));
    }