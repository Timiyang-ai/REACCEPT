public Map<String, File> identifyWorkerLogDirs(Set<File> logDirs) {
        // we could also make this static, but not to do it due to mock
        return logDirs.stream().map(Unchecked.function(logDir -> {
            Optional<File> metaFile = getMetadataFileForWorkerLogDir(logDir);

            return metaFile.map(Unchecked.function(m -> new Tuple2<>(getWorkerIdFromMetadataFile(m.getCanonicalPath()), logDir)))
                    .orElse(new Tuple2<>("", logDir));
        })).collect(toMap(Tuple2::v1, Tuple2::v2));
    }