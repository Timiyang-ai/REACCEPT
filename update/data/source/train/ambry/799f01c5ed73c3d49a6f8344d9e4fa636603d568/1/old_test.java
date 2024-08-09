@Test
  public void copyTest() throws IOException, StoreException {
    storeCopier.copy(new StoreFindTokenFactory(STORE_KEY_FACTORY).getNewFindToken());
    storeCopier.close();
    // copy the store descriptor file over
    Files.copy(new File(srcDir, StoreDescriptor.STORE_DESCRIPTOR_FILENAME).toPath(),
        new File(tgtDir, StoreDescriptor.STORE_DESCRIPTOR_FILENAME).toPath(), StandardCopyOption.REPLACE_EXISTING);
    BlobStore tgt = new BlobStore(STORE_ID, storeConfig, null, null, DISK_IO_SCHEDULER,
        new StorageManagerMetrics(new MetricRegistry()), tgtDir.getAbsolutePath(), STORE_CAPACITY, STORE_KEY_FACTORY,
        null, null, time);
    tgt.start();
    try {
      // should not be able to get expired or deleted ids
      StoreKey[] failKeys = {expiredId, deletedId};
      for (StoreKey key : failKeys) {
        try {
          tgt.get(Collections.singletonList(key), EnumSet.allOf(StoreGetOptions.class));
          fail("Should have failed to get " + key);
        } catch (StoreException e) {
          assertEquals("Unexpected StoreErrorCode", StoreErrorCodes.ID_Not_Found, e.getErrorCode());
        }
      }
      // should be able to get the put id
      StoreInfo storeInfo = tgt.get(Collections.singletonList(putId), EnumSet.noneOf(StoreGetOptions.class));
      MessageInfo messageInfo = storeInfo.getMessageReadSetInfo().get(0);
      assertEquals("Size does not match", putData.length, messageInfo.getSize());
      assertEquals("Size does not match", putData.length, storeInfo.getMessageReadSet().sizeInBytes(0));
      assertFalse("Should not be deleted or expired", messageInfo.isDeleted() || messageInfo.isExpired());
      ByteBufferChannel channel = new ByteBufferChannel(ByteBuffer.allocate(putData.length));
      storeInfo.getMessageReadSet().writeTo(0, channel, 0, putData.length);
      assertArrayEquals("Data put does not match data copied", putData, channel.getBuffer().array());
    } finally {
      tgt.shutdown();
    }
  }