@Override
  public int create(String path, @mode_t long mode, FuseFileInfo fi) {
    final AlluxioURI uri = mPathResolverCache.getUnchecked(path);
    final int flags = fi.flags.get();
    LOG.trace("create({}, {}) [Alluxio: {}]", path, Integer.toHexString(flags), uri);

    try {
      if (mOpenFiles.size() >= MAX_OPEN_FILES) {
        LOG.error("Cannot create {}: too many open files (MAX_OPEN_FILES: {})", path,
            MAX_OPEN_FILES);
        return -ErrorCodes.EMFILE();
      }

      FileOutStream os = mFileSystem.createFile(uri);
      synchronized (mOpenFiles) {
        mOpenFiles.add(new OpenFileEntry(mNextOpenFileId, path, null, os));
        fi.fh.set(mNextOpenFileId);

        // Assuming I will never wrap around (2^64 open files are quite a lot anyway)
        mNextOpenFileId += 1;
      }
      LOG.debug("{} created and opened", path);
    } catch (Throwable t) {
      LOG.error("Failed to create {}", path, t);
      return AlluxioFuseUtils.getErrorCode(t);
    }

    return 0;
  }