public void addFile(@Nullable final String path) {
        // Ignore null paths
        if (path == null) {
            return;
        }

        // Add path to Spark
        if (!files.contains(path)) {
            sparkContext.addFile(path);
            files.add(path);
        } else {
            log.debug("Skipping existing addFile path: {}", path);
        }
    }