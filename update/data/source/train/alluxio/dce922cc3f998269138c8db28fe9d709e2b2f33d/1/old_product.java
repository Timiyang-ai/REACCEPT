public CreateFileTOptions toThrift() {
    CreateFileTOptions options = new CreateFileTOptions();
    options.setBlockSizeBytes(mBlockSizeBytes);
    options.setPersisted(mWriteType.getUnderStorageType().isSyncPersist());
    options.setRecursive(mRecursive);
    options.setTtl(mTtl);
    options.setTtlExpiryAction(mTtlExpiryAction == TtlExpiryAction.FREE
        ? alluxio.thrift.TtlExpiryAction.Free
        : alluxio.thrift.TtlExpiryAction.Delete);
    return options;
  }