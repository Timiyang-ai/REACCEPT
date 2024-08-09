public void deleteUpload(@Nonnull final DataSet dataSet, @Nonnull final String fileName) throws IOException {
        final Path path = getUploadPath(dataSet, fileName);
        if (!isolatedFunction(dataSet, path, fs -> fs.delete(path, false))) {
            log.info("Delete unsuccessful for path: {}", path);
            throw new IOException("Failed to delete: " + dataSet.getId() + Path.SEPARATOR + fileName);
        }
    }