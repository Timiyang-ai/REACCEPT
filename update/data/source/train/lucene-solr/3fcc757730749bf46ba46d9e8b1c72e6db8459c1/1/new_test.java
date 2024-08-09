@Test
  public void testSyncLocalCoreWithSharedStore_syncEquivalent() throws Exception {
    CoreStorageClient blobClientSpy = Mockito.spy(storageClient);    
    SharedShardMetadataController sharedMetadataController = cc.getSharedStoreManager().getSharedShardMetadataController();
    sharedMetadataController.ensureMetadataNodeExists(collectionName, shardName);
    try {
      BlobStoreUtils.syncLocalCoreWithSharedStore(collectionName, newReplica.getCoreName(), shardName, cc);
      verify(blobClientSpy, never()).pullCoreMetadata(anyString(), anyString());
    } catch (Exception ex){
      fail("syncLocalCoreWithSharedStore failed with exception: " + ex.getMessage());
    } 
  }