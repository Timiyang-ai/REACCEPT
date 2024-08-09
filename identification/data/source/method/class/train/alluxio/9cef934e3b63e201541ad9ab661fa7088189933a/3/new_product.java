@Override
  public int chmod(String path, @mode_t long mode) {
    AlluxioURI uri = mPathResolverCache.getUnchecked(path);

    SetAttributePOptions options = SetAttributePOptions.newBuilder()
        .setMode(new alluxio.security.authorization.Mode((short) mode).toProto()).build();
    try {
      mFileSystem.setAttribute(uri, options);
    } catch (IOException | AlluxioException e) {
      LOG.error("Exception on {} of changing mode to {}", path, mode, e);
      return -ErrorCodes.EIO();
    }

    return 0;
  }