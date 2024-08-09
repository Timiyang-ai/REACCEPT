@Override
  public int flush(String path, FuseFileInfo fi) {
    LOG.trace("flush({})", path);
    final long fd = fi.fh.get();
    OpenFileEntry oe;
    synchronized (mOpenFiles) {
      oe = mOpenFiles.getFirstByField(ID_INDEX, fd);
    }
    if (oe == null) {
      LOG.error("Cannot find fd for {} in table", path);
      return -ErrorCodes.EBADFD();
    }
    if (oe.getOut() != null) {
      try {
        oe.getOut().flush();
      } catch (IOException e) {
        LOG.error("IOException on  {}", path, e);
        return -ErrorCodes.EIO();
      }
    } else {
      LOG.debug("Not flushing: {} was not open for writing", path);
    }
    return 0;
  }