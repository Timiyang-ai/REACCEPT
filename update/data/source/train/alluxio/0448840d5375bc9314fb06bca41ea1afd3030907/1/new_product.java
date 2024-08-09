public CreateDirectoryTOptions toThrift() {
    CreateDirectoryTOptions options = new CreateDirectoryTOptions();
    options.setAllowExists(mAllowExists);
    options.setMode(mMode.toShort());
    options.setRecursive(mRecursive);
    options.setPersisted(mUnderStorageType.isSyncPersist());
    return options;
  }