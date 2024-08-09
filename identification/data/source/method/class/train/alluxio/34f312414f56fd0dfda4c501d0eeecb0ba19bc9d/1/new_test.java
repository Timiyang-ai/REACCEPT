@Test
  public void persistFileTest() throws Exception {
    long fileId = 1;
    List<Long> blockIds = Lists.newArrayList(1L, 2L);

    writeFileWithBlocks(fileId, blockIds);

    // verify file persisted
    Assert.assertEquals(Arrays.asList(fileId), mManager.getPersistedFiles());

    // verify fastCopy called twice, once per block
    PowerMockito.verifyStatic(Mockito.times(2));
    BufferUtils.fastCopy(Mockito.any(ReadableByteChannel.class),
        Mockito.any(WritableByteChannel.class));

    // verify the file is not needed for another persistence
    Assert.assertFalse(mManager.needPersistence(fileId));
  }