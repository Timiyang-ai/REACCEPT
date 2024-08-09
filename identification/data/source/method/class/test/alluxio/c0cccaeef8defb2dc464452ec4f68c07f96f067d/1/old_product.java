public PermissionStatus applyUMask(FileSystemPermission umask, Configuration configuration) {
    if (!SecurityUtils.isAuthorizationEnabled(configuration)) {
      return new PermissionStatus(mUserName, mGroupName, mPermission);
    }
    FileSystemPermission newFileSystemPermission = mPermission.applyUMask(umask);
    return new PermissionStatus(mUserName, mGroupName, newFileSystemPermission);
  }