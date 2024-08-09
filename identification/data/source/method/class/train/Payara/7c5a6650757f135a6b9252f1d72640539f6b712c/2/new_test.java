    private void getListOfFiles(final FileArchive instance,
            final Set<String> expectedEntryNames,
            final Logger logger) {
        final List<String> foundEntryNames = new ArrayList<String>();

        instance.getListOfFiles(archiveDir, foundEntryNames, null, logger);

        assertEquals("Missing or unexpected entry names reported", expectedEntryNames, 
                new HashSet<String>(foundEntryNames));
    }