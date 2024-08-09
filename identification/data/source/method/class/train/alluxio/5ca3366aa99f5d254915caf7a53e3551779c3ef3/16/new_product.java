@Override
  public int create(String path, @mode_t long mode, FuseFileInfo fi) {
    final AlluxioURI uri = mPathResolverCache.getUnchecked(path);
    final int flags = fi.flags.get();
    LOG.trace("create({}, {}) [Alluxio: {}]", path, Integer.toHexString(flags), uri);

    if (uri.getName().length() > MAX_NAME_LENGTH) {
      LOG.error("Failed to create {}, file name is longer than {} characters",
          path, MAX_NAME_LENGTH);
      return -ErrorCodes.ENAMETOOLONG();
    }
    try {
      if (mOpenFiles.size() >= MAX_OPEN_FILES) {
        LOG.error("Cannot create {}: too many open files (MAX_OPEN_FILES: {})", path,
            MAX_OPEN_FILES);
        return -ErrorCodes.EMFILE();
      }
      SetAttributePOptions.Builder attributeOptionsBuilder = SetAttributePOptions.newBuilder();
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
        attributeOptionsBuilder.setGroup(groupName);
      }
      if (uid != UID) {
        String userName = AlluxioFuseUtils.getUserName(uid);
        if (userName.isEmpty()) {
          // This should never be reached since input uid is always valid
          LOG.error("Failed to get user name from uid {}", uid);
          return -ErrorCodes.EFAULT();
        }
        attributeOptionsBuilder.setOwner(userName);
      }
      SetAttributePOptions setAttributePOptions = attributeOptionsBuilder.build();
      FileOutStream os = mFileSystem.createFile(uri,
          CreateFilePOptions.newBuilder()
              .setMode(new alluxio.security.authorization.Mode((short) mode).toProto())
              .build());
      synchronized (mOpenFiles) {
        mOpenFiles.add(new OpenFileEntry(mNextOpenFileId, path, null, os));
        fi.fh.set(mNextOpenFileId);

        // Assuming I will never wrap around (2^64 open files are quite a lot anyway)
        mNextOpenFileId += 1;
      }
      if (gid != GID || uid != UID) {
        LOG.debug("Set attributes of path {} to {}", path, setAttributePOptions);
        mFileSystem.setAttribute(uri, setAttributePOptions);
      }
      LOG.debug("{} created and opened", path);
    } catch (FileAlreadyExistsException e) {
      LOG.debug("Failed to create {}, file already exists", path);
      return -ErrorCodes.EEXIST();
    } catch (InvalidPathException e) {
      LOG.debug("Failed to create {}, path is invalid", path);
      return -ErrorCodes.ENOENT();
    } catch (Throwable t) {
      LOG.error("Failed to create {}", path, t);
      return AlluxioFuseUtils.getErrorCode(t);
    }

    return 0;
  }