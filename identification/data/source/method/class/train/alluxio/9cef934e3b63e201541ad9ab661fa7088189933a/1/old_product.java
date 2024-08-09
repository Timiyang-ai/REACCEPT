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
      LOG.debug("File {} does not exist", oldPath);
      return -ErrorCodes.ENOENT();
    } catch (FileAlreadyExistsException e) {
      LOG.debug("File {} already exists", newPath);
      return -ErrorCodes.EEXIST();
    } catch (IOException e) {
      LOG.error("IOException while moving {} to {}", oldPath, newPath, e);
      return -ErrorCodes.EIO();
    } catch (AlluxioException e) {
      LOG.error("Exception while moving {} to {}", oldPath, newPath, e);
      return -ErrorCodes.EFAULT();
    } catch (Throwable e) {
      LOG.error("Unexpected exception on mv {} {}", oldPath, newPath, e);
      return -ErrorCodes.EFAULT();
    }

    return 0;
  }