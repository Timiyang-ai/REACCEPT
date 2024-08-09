public void deleteUpload(@Nonnull final String dataSetId, @Nonnull final String fileName) throws IOException {
        final DataSet dataSet = getDataSet(dataSetId).orElseThrow(DataSetNotFound::new);
        final Path path = getUploadPath(dataSet.getId(), fileName);
        if (!isolatedFunction(dataSet, path, fs -> fs.delete(path, false))) {
            log.debug("Delete unsuccessful for path: {}", path);
            throw new IOException("Failed to delete: " + dataSet.getId() + Path.SEPARATOR + fileName);
        }
    }