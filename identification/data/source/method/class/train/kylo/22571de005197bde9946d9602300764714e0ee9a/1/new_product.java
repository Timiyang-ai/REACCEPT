@Nonnull
    public List<DataSetFile> listUploads(@Nonnull final DataSet dataSet) throws IOException {
        final Path path = getUploadPath(dataSet);
        try {
            return isolatedFunction(dataSet, path, fs -> listFiles(fs, path));
        } catch (final FileNotFoundException e) {
            log.debug("Dataset directory does not exist: {}", path);
            return Collections.emptyList();
        }
    }