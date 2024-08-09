@Test
  public void toThrift() {
    Random random = new Random();
    boolean allowExists = random.nextBoolean();
    boolean recursive = random.nextBoolean();
    WriteType writeType = WriteType.NONE;

    CreateDirectoryOptions options = CreateDirectoryOptions.defaults();
    options.setAllowExists(allowExists);
    options.setRecursive(recursive);
    options.setWriteType(writeType);

    CreateDirectoryTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(allowExists, thriftOptions.isAllowExists());
    Assert.assertEquals(recursive, thriftOptions.isRecursive());
    Assert.assertEquals(writeType.getUnderStorageType().isSyncPersist(),
        thriftOptions.isPersisted());
  }