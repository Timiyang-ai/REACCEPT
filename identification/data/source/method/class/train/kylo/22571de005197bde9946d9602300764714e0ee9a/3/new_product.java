@Nonnull
    public DataSetFile createUpload(@Nonnull final DataSet dataSet, @Nonnull final String fileName, @Nonnull final InputStream in) throws IOException {
        final Path path = getUploadPath(dataSet, fileName);
        final List<DataSetFile> files = isolatedFunction(dataSet, path, fs -> {
            log.debug("Creating file [{}] for dataset {}", fileName, dataSet.getId());
            try (final FSDataOutputStream out = fs.create(path, false)) {
                IOUtils.copyLarge(in, out);
            }

            if (username != null || groupname != null) {
                log.debug("Changing owner of [{}] to {}:{}", path, username, groupname);
                fs.setOwner(path, username, groupname);
            }
            if (permission != null) {
                log.debug("Setting permissions of [{}] to {}", path, permission);
                fs.setPermission(path, permission);
            }

            return listFiles(fs, path);
        });

        if (files.size() == 1) {
            return files.get(0);
        } else {
            log.error("Failed to upload file for dataset {} at path: {}. Expected 1 file but found {} files.", dataSet.getId(), path, files.size());
            throw new IOException("Uploaded file not found");
        }
    }