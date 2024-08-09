FileSystemCommand workerHeartbeat(long workerId, List<Long> persistedFiles,
      WorkerHeartbeatOptions options)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException, IOException;