@Override
    public void initialize() throws Exception {
        super.initialize();
        if (!isFilesMatched()) {
            return;
        }
        final File baseDir = Settings.getTempDirectory();
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                final String msg = String.format("Unable to make a temporary folder '%s'", baseDir.getPath());
                throw new AnalysisException(msg);
            }
        }
        tempFileLocation = File.createTempFile("check", "tmp", baseDir);
        if (!tempFileLocation.delete()) {
            final String msg = String.format("Unable to delete temporary file '%s'.", tempFileLocation.getAbsolutePath());
            throw new AnalysisException(msg);
        }
        if (!tempFileLocation.mkdirs()) {
            final String msg = String.format("Unable to create directory '%s'.", tempFileLocation.getAbsolutePath());
            throw new AnalysisException(msg);
        }
    }