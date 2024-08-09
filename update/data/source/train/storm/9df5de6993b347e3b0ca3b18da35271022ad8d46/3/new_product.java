@VisibleForTesting
    SortedSet<Path> getDeadWorkerDirs(int nowSecs, Set<Path> logDirs) throws Exception {
        if (logDirs.isEmpty()) {
            return new TreeSet<>();
        } else {
            Set<String> aliveIds = workerLogs.getAliveIds(nowSecs);
            return workerLogs.getLogDirs(logDirs, (wid) -> !aliveIds.contains(wid));
        }
    }