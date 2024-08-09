@Test
  public void defaults() throws IOException {
    AlluxioStorageType alluxioType = AlluxioStorageType.STORE;
    UnderStorageType ufsType = UnderStorageType.SYNC_PERSIST;
    mConf.set(PropertyKey.USER_BLOCK_SIZE_BYTES_DEFAULT, "64MB");
    mConf.set(PropertyKey.USER_FILE_WRITE_TYPE_DEFAULT, WriteType.CACHE_THROUGH.toString());
    mConf.set(PropertyKey.USER_FILE_WRITE_TIER_DEFAULT, Constants.LAST_TIER);

    OutStreamOptions options = OutStreamOptions.defaults(mConf);

    assertEquals(alluxioType, options.getAlluxioStorageType());
    assertEquals(64 * Constants.MB, options.getBlockSizeBytes());
    assertTrue(options.getLocationPolicy() instanceof LocalFirstPolicy);
    assertEquals("test_user", options.getOwner());
    assertEquals("test_group", options.getGroup());
    assertEquals(ModeUtils.applyFileUMask(Mode.defaults(),
        mConf.get(PropertyKey.SECURITY_AUTHORIZATION_PERMISSION_UMASK)), options.getMode());
    assertEquals(Constants.NO_TTL, options.getCommonOptions().getTtl());
    assertEquals(TtlAction.DELETE, options.getCommonOptions().getTtlAction());
    assertEquals(ufsType, options.getUnderStorageType());
    assertEquals(WriteType.CACHE_THROUGH, options.getWriteType());
    assertEquals(Constants.LAST_TIER, options.getWriteTier());
  }