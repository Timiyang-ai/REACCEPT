@Override
  public int getattr(String path, FileStat stat) {
    final AlluxioURI turi = mPathResolverCache.getUnchecked(path);
    LOG.trace("getattr({}) [Alluxio: {}]", path, turi);
    try {
      if (!mFileSystem.exists(turi)) {
        return -ErrorCodes.ENOENT();
      }
      final URIStatus status = mFileSystem.getStatus(turi);
      stat.st_size.set(status.getLength());

      final long ctime_sec = status.getLastModificationTimeMs() / 1000;
      //keeps only the "residual" nanoseconds not caputred in
      // citme_sec
      final long ctime_nsec = (status.getLastModificationTimeMs() % 1000) * 1000;

      stat.st_ctim.tv_sec.set(ctime_sec);
      stat.st_ctim.tv_nsec.set(ctime_nsec);
      stat.st_mtim.tv_sec.set(ctime_sec);
      stat.st_mtim.tv_nsec.set(ctime_nsec);

      // for shell-based group mapping, use the uid and gid of the corresponding user registered in
      // unix; otherwise use uid and gid of the user running alluxio-fuse
      if(GroupMappingService.Factory.get() instanceof ShellBasedUnixGroupsMapping) {
        String owner = status.getOwner();
        long[] uidAndGid = AlluxioFuseUtils.getUidAndGid(owner);
        stat.st_uid.set(uidAndGid[0]);
        stat.st_gid.set(uidAndGid[1]);
      } else {
        stat.st_uid.set(UID_AND_GID[0]);
        stat.st_gid.set(UID_AND_GID[1]);
      }

      int mode = status.getMode();
      if (status.isFolder()) {
        mode |= FileStat.S_IFDIR;
      } else {
        mode |= FileStat.S_IFREG;
      }
      stat.st_mode.set(mode);

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