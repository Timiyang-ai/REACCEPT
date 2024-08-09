@Test
  @SuppressWarnings("unchecked")
  public void persistFileTest() throws Exception {
    long fileId = 1;
    List<Long> blockIds = Lists.newArrayList(1L, 2L);

    // mock block worker
    BlockWorker blockWorker = Mockito.mock(BlockWorker.class);
    FileInfo fileInfo = new FileInfo();
    fileInfo.setPath("test");
    Mockito.when(blockWorker.getFileInfo(fileId)).thenReturn(fileInfo);
    BlockReader reader = Mockito.mock(BlockReader.class);
    for (long blockId : blockIds) {
      Mockito.when(blockWorker.lockBlock(Sessions.CHECKPOINT_SESSION_ID, blockId))
          .thenReturn(blockId);
      Mockito
          .when(blockWorker.readBlockRemote(Sessions.CHECKPOINT_SESSION_ID, blockId, blockId))
          .thenReturn(reader);
    }

    FileDataManager manager = new FileDataManager(blockWorker);

    // mock ufs
    UnderFileSystem ufs = Mockito.mock(UnderFileSystem.class);
    String ufsRoot = new Configuration().get(Constants.UNDERFS_ADDRESS);
    Mockito.when(ufs.exists(ufsRoot)).thenReturn(true);
    Whitebox.setInternalState(manager, "mUfs", ufs);
    OutputStream outputStream = Mockito.mock(OutputStream.class);

    // mock BufferUtils
    PowerMockito.mockStatic(BufferUtils.class);

    String dstPath = PathUtils.concatPath(ufsRoot, fileInfo.getPath());
    Mockito.when(ufs.create(dstPath)).thenReturn(outputStream);
    Mockito.when(ufs.create(Mockito.anyString(), Mockito.any(PermissionStatus.class)))
        .thenReturn(outputStream);

    manager.lockBlocks(fileId, blockIds);
    manager.persistFile(fileId, blockIds);

    // verify file persisted
    Set<Long> persistedFiles = (Set<Long>) Whitebox.getInternalState(manager, "mPersistedFiles");
    Assert.assertEquals(Sets.newHashSet(fileId), persistedFiles);

    // verify fastCopy called twice, once per block
    PowerMockito.verifyStatic(Mockito.times(2));
    BufferUtils.fastCopy(Mockito.any(ReadableByteChannel.class),
        Mockito.any(WritableByteChannel.class));

    // verify the file is not needed for another persistence
    Assert.assertFalse(manager.needPersistence(fileId));
  }