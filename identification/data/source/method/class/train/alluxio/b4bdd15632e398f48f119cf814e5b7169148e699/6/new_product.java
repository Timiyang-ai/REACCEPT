@Override
  public int chown(String path, @uid_t long uid, @gid_t long gid) {
    if (!mIsUserGroupTranslation) {
      LOG.info("Cannot change the owner of path {}. Please set {} to be true to enable "
          + "user group translation in Alluxio-Fuse.",
          path, PropertyKey.FUSE_USER_GROUP_TRANSLATION_ENABLED.getName());
      return -ErrorCodes.ENOSYS();
    }

    try {
      String groupName = AlluxioFuseUtils.getGroupName(gid);
      if (groupName.isEmpty()) {
        // This should never be reached since input gid is always valid
        // If user chown without group name, the primary group gid of the user name will be provided
        LOG.error("Failed to get group name from gid {}.", gid);
        return -ErrorCodes.EFAULT();
      }

      SetAttributeOptions options = SetAttributeOptions.defaults().setGroup(groupName);
      final AlluxioURI uri = mPathResolverCache.getUnchecked(path);

      if (uid != -1 && uid != 4294967295L) {
        // 4294967295 is just unsigned long -1, -1 means that uid is not set
        // 4294967295 or -1 occurs when chown without user name or chgrp
        // Please view https://github.com/SerCeMan/jnr-fuse/issues/67 for more details
        String userName = AlluxioFuseUtils.getUserName(uid);
        if (userName.isEmpty()) {
          // This should never be reached
          LOG.error("Failed to get user name from uid {}", uid);
          return -ErrorCodes.EFAULT();
        }
        options.setOwner(userName);
        LOG.info("Change owner and group of file {} to {}:{}", path, userName, groupName);
      } else {
        LOG.info("Change group of file {} to {}", path, groupName);
      }

      mFileSystem.setAttribute(uri, options);
    } catch (IOException | AlluxioException e) {
      LOG.error("Exception on {}", path, e);
      return -ErrorCodes.EIO();
    }
    return 0;
  }