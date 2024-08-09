@Test
  public void toThrift() {
    Random random = new Random();
    long blockSize = random.nextLong();
    FileWriteLocationPolicy policy = new RoundRobinPolicy();
    Mode mode = new Mode((short) random.nextInt());
    boolean recursive = random.nextBoolean();
    long ttl = random.nextLong();
    int writeTier = random.nextInt();
    WriteType writeType = WriteType.NONE;

    CreateFileOptions options = CreateFileOptions.defaults();
    options.setBlockSizeBytes(blockSize);
    options.setLocationPolicy(policy);
    options.setMode(mode);
    options.setRecursive(recursive);
    options.setTtl(ttl);
    options.setTtlAction(TtlAction.FREE);
    options.setWriteTier(writeTier);
    options.setWriteType(writeType);

    CreateFileTOptions thriftOptions = options.toThrift();
    Assert.assertEquals(blockSize, thriftOptions.getBlockSizeBytes());
    Assert.assertEquals(recursive, thriftOptions.isRecursive());
    Assert.assertEquals(writeType.isThrough(), thriftOptions.isPersisted());
    Assert.assertEquals(ttl, thriftOptions.getTtl());
    Assert.assertEquals(alluxio.thrift.TTtlAction.Free, thriftOptions.getTtlAction());
    Assert.assertEquals(mode.toShort(), thriftOptions.getMode());
  }