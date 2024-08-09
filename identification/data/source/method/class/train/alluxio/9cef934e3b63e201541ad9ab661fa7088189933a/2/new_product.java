@Override
  public int rename(String oldPath, String newPath) {
    final AlluxioURI oldUri = mPathResolverCache.getUnchecked(oldPath);
    final AlluxioURI newUri = mPathResolverCache.getUnchecked(newPath);
    LOG.trace("rename({}, {}) [Alluxio: {}, {}]", oldPath, newPath, oldUri, newUri);

    try {
      mFileSystem.rename(oldUri, newUri);
      synchronized (mOpenFiles) {
        if (mOpenFiles.contains(PATH_INDEX, oldPath)) {
          OpenFileEntry oe = mOpenFiles.getFirstByField(PATH_INDEX, oldPath);
          oe.setPath(newPath);
        }
      }
    } catch (FileDoesNotExistException e) {
      LOG.debug("Failed to rename {} to {}, file {} does not exist", oldPath, newPath, oldPath);
      return -ErrorCodes.ENOENT();
    } catch (FileAlreadyExistsException e) {
      LOG.debug("Failed to rename {} to {}, file {} already exists", oldPath, newPath, newPath);
      return -ErrorCodes.EEXIST();
    } catch (Throwable t) {
      LOG.error("Failed to rename {} to {}", oldPath, newPath, t);
      return AlluxioFuseUtils.getErrorCode(t);
    }

    return 0;
  }