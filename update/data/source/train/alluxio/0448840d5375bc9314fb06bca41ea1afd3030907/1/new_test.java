@Test
  public void toThrift() {
    Random random = new Random();
    boolean allowExists = random.nextBoolean();
    boolean recursive = random.nextBoolean();
    Mode mode = new Mode((short) 0123);
    WriteType writeType = WriteType.NONE;

    CreateDirectoryOptions options = CreateDirectoryOptions.defaults();
    options.setAllowExists(allowExists);
    options.setMode(mode);
    options.setRecursive(recursive);
    options.setWriteType(writeType);

    CreateDirectoryTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(allowExists, thriftOptions.isAllowExists());
    Assert.assertEquals(recursive, thriftOptions.isRecursive());
    Assert.assertEquals(writeType.getUnderStorageType().isSyncPersist(),
        thriftOptions.isPersisted());
    Assert.assertEquals(mode.toShort(), thriftOptions.getMode());
  }