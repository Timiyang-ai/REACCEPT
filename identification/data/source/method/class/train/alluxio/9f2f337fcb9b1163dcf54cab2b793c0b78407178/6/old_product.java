public synchronized FileSystemCommand workerHeartbeat(long workerId, List<Long> persistedFiles)
      throws FileDoesNotExistException, InvalidPathException, AccessControlException {
    for (long fileId : persistedFiles) {
      setState(getPath(fileId), SetAttributeOptions.defaults().setPersisted(true));
    }

    // get the files for the given worker to checkpoint
    List<PersistFile> filesToCheckpoint = pollFilesToCheckpoint(workerId);
    if (!filesToCheckpoint.isEmpty()) {
      LOG.debug("Sent files {} to worker {} to persist", filesToCheckpoint, workerId);
    }
    FileSystemCommandOptions options = new FileSystemCommandOptions();
    options.setPersistOptions(new PersistCommandOptions(filesToCheckpoint));
    return new FileSystemCommand(CommandType.Persist, options);
  }