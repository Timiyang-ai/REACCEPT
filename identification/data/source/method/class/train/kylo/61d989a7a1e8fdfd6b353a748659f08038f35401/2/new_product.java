public void addFile(@Nullable final String path) {
        // Ignore null file
        if (path == null) {
            log.debug("Ignoring null file");
            return;
        }

        // Ignore duplicate file
        final String name = FilenameUtils.getName(path);
        if (files.contains(name)) {
            log.debug("Ignoring existing file: {}", path);
            return;
        }

        // Ignore invalid file
        URI uri;
        try {
            uri = URI.create(path);
            if (uri.getScheme() == null) {
                uri = URI.create("file:///").resolve(new File("").getAbsolutePath() + "/").resolve(path);
            }
        } catch (final IllegalArgumentException e) {
            log.debug("Ignoring invalid path: {}", path, e);
            return;
        }

        // Ignore missing file
        try {
            if (!FileSystemUtil.fileExists(uri, sparkContext.hadoopConfiguration())) {
                log.debug("Ignoring missing file: {}", path);
                return;
            }
        } catch (final IOException e) {
            log.debug("Failed to determine if file [{}] exists: {}", path, e, e);
        }

        // Add file to Spark context
        log.debug("Updating Spark files with: {}", path);
        sparkContext.addFile(path);
        files.add(name);
    }