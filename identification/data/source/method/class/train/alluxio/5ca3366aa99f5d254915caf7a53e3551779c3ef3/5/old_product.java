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
        boolean writing;
        synchronized (mOpenFiles) {
          // Fuse release() returns but does not finish is not considered as writing here
          writing = mOpenFiles.contains(PATH_INDEX, path);
        }
        // Always block waiting for file to be completed except when the file is writing
        // We do not want to block the writing process
        if (!writing && !waitForFileCompleted(turi)) {
          LOG.error("File {} is not completed", path);
        }
        status = mFileSystem.getStatus(turi);
      }
      long size = status.getLength();
      stat.st_size.set(size);

      // Sets block number to fulfill du command needs
      // `st_blksize` is ignored in `getattr` according to
      // https://github.com/libfuse/libfuse/blob/d4a7ba44b022e3b63fc215374d87ed9e930d9974/include/fuse.h#L302
      // According to http://man7.org/linux/man-pages/man2/stat.2.html,
      // `st_blocks` is the number of 512B blocks allocated
      stat.st_blocks.set((int) Math.ceil((double) size / 512));

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