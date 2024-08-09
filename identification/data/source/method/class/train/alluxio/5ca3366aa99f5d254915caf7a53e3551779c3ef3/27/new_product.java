@Override
  public int getattr(String path, FileStat stat) {
    final AlluxioURI turi = mPathResolverCache.getUnchecked(path);
    LOG.trace("getattr({}) [Alluxio: {}]", path, turi);
    try {
      if (!mFileSystem.exists(turi)) {
        return -ErrorCodes.ENOENT();
      }
      URIStatus status = mFileSystem.getStatus(turi);
      if (!status.isCompleted()) {
        if (!waitForFileCompleted(turi)) {
          LOG.error("File {} is not completed", path);
        }
        status = mFileSystem.getStatus(turi);
      }
      long size = status.getLength();
      long blockSize = status.getBlockSizeBytes();
      stat.st_size.set(size);
      // Sets block number and block size to fulfill du command needs
      stat.st_blksize.set(blockSize);
      // Does not consider replications
      stat.st_blocks.set((int) Math.ceil((double) size / blockSize));

      final long ctime_sec = status.getLastModificationTimeMs() / 1000;
      // Keeps only the "residual" nanoseconds not caputred in citme_sec
      final long ctime_nsec = (status.getLastModificationTimeMs() % 1000) * 1000;

      stat.st_ctim.tv_sec.set(ctime_sec);
      stat.st_ctim.tv_nsec.set(ctime_nsec);
      stat.st_mtim.tv_sec.set(ctime_sec);
      stat.st_mtim.tv_nsec.set(ctime_nsec);

      if (mIsUserGroupTranslation) {
        // Translate the file owner/group to unix uid/gid
        // Show as uid==-1 (nobody) if owner does not exist in unix
        // Show as gid==-1 (nogroup) if group does not exist in unix
        stat.st_uid.set(AlluxioFuseUtils.getUid(status.getOwner()));
        stat.st_gid.set(AlluxioFuseUtils.getGidFromGroupName(status.getGroup()));
      } else {
        stat.st_uid.set(UID);
        stat.st_gid.set(GID);
      }

      int mode = status.getMode();
      if (status.isFolder()) {
        mode |= FileStat.S_IFDIR;
      } else {
        mode |= FileStat.S_IFREG;
      }
      stat.st_mode.set(mode);
      stat.st_nlink.set(1);
    } catch (InvalidPathException e) {
      LOG.debug("Invalid path {}", path, e);
      return -ErrorCodes.ENOENT();
    } catch (FileDoesNotExistException e) {
      LOG.debug("File does not exist {}", path, e);
      return -ErrorCodes.ENOENT();
    } catch (IOException e) {
      LOG.error("IOException on {}", path, e);
      return -ErrorCodes.EIO();
    } catch (AlluxioException e) {
      LOG.error("AlluxioException on {}", path, e);
      return -ErrorCodes.EFAULT();
    } catch (Throwable e) {
      LOG.error("Unexpected exception on {}", path, e);
      return -ErrorCodes.EFAULT();
    }

    return 0;
  }