@Override
    public void initialize() throws Exception {
        final File baseDir = Settings.getTempDirectory();
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                final String msg = String.format("Unable to make a temporary folder '%s'", baseDir.getPath());
                throw new AnalysisException(msg);
            }
        }
        tempFileLocation = File.createTempFile("check", "tmp", baseDir);
        if (!tempFileLocation.delete()) {
            throw new AnalysisException("Unable to delete temporary file '" + tempFileLocation.getAbsolutePath() + "'.");
        }
        if (!tempFileLocation.mkdirs()) {
            throw new AnalysisException("Unable to create directory '" + tempFileLocation.getAbsolutePath() + "'.");
        }
    }