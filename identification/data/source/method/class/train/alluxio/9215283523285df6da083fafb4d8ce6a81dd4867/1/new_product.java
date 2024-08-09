public CreateFileTOptions toThrift() {
    CreateFileTOptions options = new CreateFileTOptions();
    options.setBlockSizeBytes(mBlockSizeBytes);
    options.setPersisted(mWriteType.isThrough());
    options.setRecursive(mRecursive);
    options.setTtl(mTtl);
    options.setTtlAction(ThriftUtils.toThrift(mTtlAction));
    if (mMode != null) {
      options.setMode(mMode.toShort());
    }
    return options;
  }