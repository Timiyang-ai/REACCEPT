@Test
  public void copyTest() throws Exception {
    storeCopier.copy(new StoreFindTokenFactory(STORE_KEY_FACTORY).getNewFindToken());
    storeCopier.close();
    // copy the store descriptor file over
    StoreMetrics storeMetrics = new StoreMetrics(new MetricRegistry());
    Files.copy(new File(srcDir, StoreDescriptor.STORE_DESCRIPTOR_FILENAME).toPath(),
        new File(tgtDir, StoreDescriptor.STORE_DESCRIPTOR_FILENAME).toPath(), StandardCopyOption.REPLACE_EXISTING);
    BlobStore tgt =
        new BlobStore(STORE_ID, storeConfig, null, null, DISK_IO_SCHEDULER, StoreTestUtils.DEFAULT_DISK_SPACE_ALLOCATOR,
            storeMetrics, storeMetrics, tgtDir.getAbsolutePath(), STORE_CAPACITY, STORE_KEY_FACTORY, null, null, time);
    tgt.start();
    try {
      // should not be able to get expired or deleted ids
      StoreKey[] failKeys = {expiredId, deletedId, putTtlUpdatedAndDeletedId};
      for (StoreKey key : failKeys) {
        try {
          tgt.get(Collections.singletonList(key), EnumSet.allOf(StoreGetOptions.class));
          fail("Should have failed to get " + key);
        } catch (StoreException e) {
          assertEquals("Unexpected StoreErrorCode", StoreErrorCodes.ID_Not_Found, e.getErrorCode());
        }
      }
      // should be able to get the non expired, non deleted entries
      Map<StoreKey, Pair<byte[], Long>> successKeys = new HashMap<>();
      successKeys.put(permanentPutId, new Pair<>(permanentPutData, Utils.Infinite_Time));
      successKeys.put(putAndTtlUpdatedId, new Pair<>(putAndTtlUpdatedData, Utils.Infinite_Time));
      successKeys.put(temporaryPutId, new Pair<>(temporaryPutData, temporaryPutExpiryTimeMs));
      for (Map.Entry<StoreKey, Pair<byte[], Long>> entry : successKeys.entrySet()) {
        StoreInfo storeInfo = tgt.get(Collections.singletonList(entry.getKey()), EnumSet.noneOf(StoreGetOptions.class));
        MessageInfo messageInfo = storeInfo.getMessageReadSetInfo().get(0);
        byte[] data = entry.getValue().getFirst();
        assertEquals("Size does not match", data.length, messageInfo.getSize());
        assertEquals("Size does not match", data.length, storeInfo.getMessageReadSet().sizeInBytes(0));
        assertFalse("Should not be deleted or expired", messageInfo.isDeleted() || messageInfo.isExpired());
        assertEquals("Ttl update flag not as expected", putAndTtlUpdatedId.equals(entry.getKey()),
            messageInfo.isTtlUpdated());
        assertEquals("Expiration time does not match", entry.getValue().getSecond().longValue(),
            messageInfo.getExpirationTimeInMs());
        ByteBufferChannel channel = new ByteBufferChannel(ByteBuffer.allocate(data.length));
        storeInfo.getMessageReadSet().writeTo(0, channel, 0, data.length);
        assertArrayEquals("Data put does not match data copied", data, channel.getBuffer().array());
      }
    } finally {
      tgt.shutdown();
    }
  }