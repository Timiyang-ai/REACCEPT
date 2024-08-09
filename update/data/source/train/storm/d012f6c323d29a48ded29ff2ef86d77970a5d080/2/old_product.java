@VisibleForTesting
    SortedSet<File> getDeadWorkerDirs(int nowSecs, Set<File> logDirs) throws Exception {
        if (logDirs.isEmpty()) {
            return new TreeSet<>();
        } else {
            Set<String> aliveIds = workerLogs.getAliveIds(nowSecs);
            Map<String, File> idToDir = workerLogs.identifyWorkerLogDirs(logDirs);

            return idToDir.entrySet().stream()
                    .filter(entry -> !aliveIds.contains(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .collect(toCollection(TreeSet::new));
        }
    }