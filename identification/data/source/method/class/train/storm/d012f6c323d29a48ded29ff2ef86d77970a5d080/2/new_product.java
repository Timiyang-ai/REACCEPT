public SortedSet<File> getLogDirs(Set<File> logDirs, Predicate<String> predicate) {
        // we could also make this static, but not to do it due to mock
        TreeSet<File> ret = new TreeSet<>();
        for (File logDir: logDirs) {
            String workerId = "";
            try {
                Optional<File> metaFile = getMetadataFileForWorkerLogDir(logDir);
                if (metaFile.isPresent()) {
                    workerId = getWorkerIdFromMetadataFile(metaFile.get().getCanonicalPath());
                    if (workerId == null) {
                        workerId = "";
                    }
                }
            } catch (IOException e) {
                LOG.warn("Error trying to find worker.yaml in {}", logDir, e);
            }
            if (predicate.test(workerId)) {
                ret.add(logDir);
            }
        }
        return ret;
    }