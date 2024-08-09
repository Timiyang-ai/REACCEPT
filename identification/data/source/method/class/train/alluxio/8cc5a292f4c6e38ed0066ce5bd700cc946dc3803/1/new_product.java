FileSystemCommand workerHeartbeat(long workerId, List<Long> persistedFiles,
      WorkerHeartbeatContext context)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException, IOException;