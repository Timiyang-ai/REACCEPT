public CreateDirectoryTOptions toThrift() {
    CreateDirectoryTOptions options = new CreateDirectoryTOptions();
    options.setAllowExists(mAllowExists);
    options.setRecursive(mRecursive);
    options.setTtl(mTtl);
    options.setTtlAction(TtlAction.toThrift(mTtlAction));
    options.setPersisted(mWriteType.isThrough());
    if (mMode != null) {
      options.setMode(mMode.toShort());
    }
    options.setCommonOptions(mCommonOptions.toThrift());
    return options;
  }