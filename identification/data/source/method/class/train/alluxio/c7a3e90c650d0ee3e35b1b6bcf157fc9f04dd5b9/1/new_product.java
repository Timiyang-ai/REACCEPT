public static long createFileId(long containerId) {
    long id = BlockId.createBlockId(containerId, BlockId.getMaxSequenceNumber());
    if (id == INVALID_FILE_ID) {
      // Right now, there's not much we can do if the file id we're returning is -1, since the file
      // id is completely determined by the container id passed in. However, by the current
      // algorithm, -1 will be the last file id generated, so the chances somebody will get to that
      // are slim. For now we just log it.
      LOG.warn("Created file id -1, which is invalid");
    }
    return id;
  }