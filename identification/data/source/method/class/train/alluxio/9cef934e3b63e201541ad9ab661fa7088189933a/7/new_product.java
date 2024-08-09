@Override
  public int rename(String oldPath, String newPath) {
    final AlluxioURI oldUri = mPathResolverCache.getUnchecked(oldPath);
    final AlluxioURI newUri = mPathResolverCache.getUnchecked(newPath);
    final String name = newUri.getName();
    LOG.trace("rename({}, {}) [Alluxio: {}, {}]", oldPath, newPath, oldUri, newUri);

    if (name.length() > MAX_NAME_LENGTH) {
      LOG.error("Failed to rename {} to {}, name {} is longer than {} characters",
          oldPath, newPath, name, MAX_NAME_LENGTH);
      return -ErrorCodes.ENAMETOOLONG();
    }
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