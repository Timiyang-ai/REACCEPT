@Test
  public void testSyncLocalCoreWithSharedStore_syncEquivalent() throws Exception {
    CoreContainer ccSpy = Mockito.spy(cc);
    ZkController zkSpy = Mockito.spy(zk);
    BlobStorageProvider blobProvideMock = Mockito.mock(BlobStorageProvider.class);
    CoreStorageClient blobClientMock = Mockito.mock(CoreStorageClient.class);
    
    Mockito.when(ccSpy.getZkController()).thenReturn(zkSpy);
    Mockito.when(zkSpy.getBlobStorageProvider()).thenReturn(blobProvideMock);
    Mockito.when(blobProvideMock.getDefaultClient()).thenReturn(blobClientMock);
    
    SharedShardMetadataController sharedMetadataController = zk.getSharedShardMetadataController();
    sharedMetadataController.ensureMetadataNodeExists(collectionName, shardName);
    try {
      BlobStoreUtils.syncLocalCoreWithSharedStore(collectionName, newReplica.getCoreName(),shardName, ccSpy);
      verify(blobClientMock,never()).pullCoreMetadata(anyString(),anyString());
    } catch (Exception ex){
      fail("syncLocalCoreWithSharedStore failed with exception: " + ex.getMessage() );
    } 
  }