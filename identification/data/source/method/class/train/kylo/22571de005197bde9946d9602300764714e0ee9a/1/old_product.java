@Nonnull
    public List<DataSetFile> listUploads(@Nonnull final String dataSetId) throws IOException {
        final DataSet dataSet = getDataSet(dataSetId).orElseThrow(DataSetNotFound::new);
        final Path path = getUploadPath(dataSet.getId());

        try {
            return isolatedFunction(dataSet, path, fs -> listFiles(fs, path));
        } catch (final FileNotFoundException e) {
            log.debug("Dataset directory does not exist: {}", path);
            return Collections.emptyList();
        }
    }