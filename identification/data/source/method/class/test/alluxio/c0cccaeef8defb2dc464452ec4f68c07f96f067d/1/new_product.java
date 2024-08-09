public PermissionStatus applyUMask(FileSystemPermission umask) {
    FileSystemPermission newFileSystemPermission = mPermission.applyUMask(umask);
    return new PermissionStatus(mUserName, mGroupName, newFileSystemPermission);
  }