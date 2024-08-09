public CreateFileTOptions toThrift() {
    CreateFileTOptions options = new CreateFileTOptions();
    options.setBlockSizeBytes(mBlockSizeBytes);
    options.setPersisted(mWriteType.getUnderStorageType().isSyncPersist());
    options.setRecursive(mRecursive);
    options.setTtl(mTtl);
    options.setTtlAction(ThriftUtils.toThrift(mTtlAction));
    return options;
  }