@Override
  public int chown(String path, @uid_t long uid, @gid_t long gid) {
    if (!mIsUserGroupTranslation) {
      LOG.info("Cannot change the owner of path {}. Please set {} to be true to enable "
          + "user group translation in Alluxio-Fuse.",
          path, PropertyKey.FUSE_USER_GROUP_TRANSLATION_ENABLED.getName());
      return -ErrorCodes.ENOSYS();
    }

    try {
      SetAttributePOptions.Builder optionsBuilder = SetAttributePOptions.newBuilder();
      final AlluxioURI uri = mPathResolverCache.getUnchecked(path);

      String userName = "";
      if (uid != ID_NOT_SET_VALUE && uid != ID_NOT_SET_VALUE_UNSIGNED) {
        userName = AlluxioFuseUtils.getUserName(uid);
        if (userName.isEmpty()) {
          // This should never be reached
          LOG.error("Failed to get user name from uid {}", uid);
          return -ErrorCodes.EFAULT();
        }
        optionsBuilder.setOwner(userName);
      }

      String groupName = "";
      if (gid != ID_NOT_SET_VALUE && gid != ID_NOT_SET_VALUE_UNSIGNED) {
        groupName = AlluxioFuseUtils.getGroupName(gid);
        if (groupName.isEmpty()) {
          // This should never be reached
          LOG.error("Failed to get group name from gid {}", gid);
          return -ErrorCodes.EFAULT();
        }
        optionsBuilder.setGroup(groupName);
      } else if (!userName.isEmpty()) {
        groupName = AlluxioFuseUtils.getGroupName(userName);
        optionsBuilder.setGroup(groupName);
      }

      if (userName.isEmpty() && groupName.isEmpty()) {
        // This should never be reached
        LOG.info("Unable to change owner and group of file {} when uid is {} and gid is {}", path,
            userName, groupName);
      } else if (userName.isEmpty()) {
        LOG.info("Change group of file {} to {}", path, groupName);
        mFileSystem.setAttribute(uri, optionsBuilder.build());
      } else {
        LOG.info("Change owner of file {} to {}", path, groupName);
        mFileSystem.setAttribute(uri, optionsBuilder.build());
      }
    } catch (IOException | AlluxioException e) {
      LOG.error("Exception on {}", path, e);
      return -ErrorCodes.EIO();
    }
    return 0;
  }