protected static void putFilename(final String formDataFilename, final String defaultFilename, final DBObject target) {
        // a filename attribute in optional properties overrides the provided part's filename 
        String filename = target.containsField(FILENAME)
                && target.get(FILENAME) instanceof String
                ? (String) target.get(FILENAME)
                : formDataFilename;
        if (filename == null || filename.isEmpty()) {
            LOGGER.debug("No filename in neither multipart content disposition header nor in properties! Using default value");
            filename = defaultFilename;
        }
        target.put(FILENAME, filename);
    }