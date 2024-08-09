@Override
  public int chown(String path, @uid_t long uid, @gid_t long gid) {
    if (!mIsUserGroupTranslation) {
      LOG.info("Cannot change the owner of path {}. Please set {} to be true to enable "
          + "user group translation in Alluxio-Fuse.",
          path, PropertyKey.FUSE_USER_GROUP_TRANSLATION_ENABLED.getName());
      return -ErrorCodes.ENOSYS();
    }
    try {
      String userName = AlluxioFuseUtils.getUserName(uid);
      String groupName = AlluxioFuseUtils.getGroupName(gid);
      // Uid and gid should exist in the unix to get valid user name and group name
      if (userName.isEmpty()) {
        LOG.error("Failed to get user name from uid {}", uid);
        return -ErrorCodes.EFAULT();
      }
      if (groupName.isEmpty()) {
        LOG.error("Failed to get group name from gid {}.", gid);
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