public PermissionStatus applyUMask(FileSystemPermission umask) {
    mPermission = mPermission.applyUMask(umask);
    return this;
  }