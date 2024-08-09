@Override
  public int create(String path, @mode_t long mode, FuseFileInfo fi) {
    final AlluxioURI uri = mPathResolverCache.getUnchecked(path);
    final int flags = fi.flags.get();
    LOG.trace("create({}, {}) [Alluxio: {}]", path, Integer.toHexString(flags), uri);

    try {
      SetAttributeOptions options = SetAttributeOptions.defaults();
      FuseContext fc = getContext();
      long uid = fc.uid.get();
      long gid = fc.gid.get();

      if (gid != GID) {
        String groupName = AlluxioFuseUtils.getGroupName(gid);
        if (groupName.isEmpty()) {
          // This should never be reached since input gid is always valid
          LOG.error("Failed to get group name from gid {}.", gid);
          return -ErrorCodes.EFAULT();
        }
        options.setGroup(groupName);
      }
      if (uid != UID) {
        String userName = AlluxioFuseUtils.getUserName(uid);
        if (userName.isEmpty()) {
          // This should never be reached since input uid is always valid
          LOG.error("Failed to get user name from uid {}", uid);
          return -ErrorCodes.EFAULT();
        }
        options.setOwner(userName);
      }
      synchronized (mOpenFiles) {
        if (mOpenFiles.size() >= MAX_OPEN_FILES) {
          LOG.error("Cannot open {}: too many open files (MAX_OPEN_FILES: {})", uri,
              MAX_OPEN_FILES);
          return -ErrorCodes.EMFILE();
        }

        mOpenFiles.add(new OpenFileEntry(mNextOpenFileId, path,
            null, mFileSystem.createFile(uri,
            CreateFileOptions.defaults().setMode(new Mode((short) mode)))));
        LOG.debug("Alluxio OutStream created for {}", path);
        fi.fh.set(mNextOpenFileId);

        // Assuming I will never wrap around (2^64 open files are quite a lot anyway)
        mNextOpenFileId += 1;
      }
      if (gid != GID || uid != UID) {
        LOG.debug("{} setattr {}", path, options);
        mFileSystem.setAttribute(uri, options);
      }
      LOG.debug("{} created and opened", path);
    } catch (FileAlreadyExistsException e) {
      LOG.debug("File {} already exists", uri, e);
      return -ErrorCodes.EEXIST();
    } catch (IOException e) {
      LOG.error("IOException on  {}", uri, e);
      return -ErrorCodes.EIO();
    } catch (AlluxioException e) {
      LOG.error("AlluxioException on {}", uri, e);
      return -ErrorCodes.EFAULT();
    } catch (Throwable e) {
      LOG.error("Unexpected exception on {}", path, e);
      return -ErrorCodes.EFAULT();
    }

    return 0;
  }