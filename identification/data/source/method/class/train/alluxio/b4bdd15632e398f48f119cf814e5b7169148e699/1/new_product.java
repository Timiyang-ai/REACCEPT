@Override
  public int chown(String path, @uid_t long uid, @gid_t long gid) {
    if (!mIsShellGroupMapping) {
      LOG.info("Cannot change the owner of path {} because the group mapping is not shell based",
          path);
      // not supported if the group mapping is not shell based
      return -ErrorCodes.ENOSYS();
    }
    try {
      String userName = AlluxioFuseUtils.getUserName(uid);
      String groupName = AlluxioFuseUtils.getGroupName(uid);
      if (userName.isEmpty()) {
        LOG.error("Failed to get user name from uid {}", uid);
        return -ErrorCodes.EFAULT();
      }
      if (groupName.isEmpty()) {
        LOG.error("Failed to get group name from uid {}", uid);
        return -ErrorCodes.EFAULT();
      }
      SetAttributeOptions options =
          SetAttributeOptions.defaults().setGroup(groupName).setOwner(userName);
      final AlluxioURI uri = mPathResolverCache.getUnchecked(path);
      LOG.info("Change owner and group of file {} to {}:{}", path, userName, groupName);

      mFileSystem.setAttribute(uri, options);
    } catch (IOException | AlluxioException e) {
      LOG.error("Exception on {}", path, e);
      return -ErrorCodes.EIO();
    }
    return 0;
  }