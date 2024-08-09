@Override
  public int unlink(String path) {
    LOG.trace("unlink({})", path);
    return rmInternal(path);
  }