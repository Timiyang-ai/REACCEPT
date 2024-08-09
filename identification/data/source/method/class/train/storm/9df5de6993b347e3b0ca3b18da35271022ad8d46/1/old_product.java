@VisibleForTesting
    SortedSet<File> getDeadWorkerDirs(int nowSecs, Set<File> logDirs) throws Exception {
        if (logDirs.isEmpty()) {
            return new TreeSet<>();
        } else {
            Set<String> aliveIds = workerLogs.getAliveIds(nowSecs);
            return workerLogs.getLogDirs(logDirs, (wid) -> !aliveIds.contains(wid));
        }
    }