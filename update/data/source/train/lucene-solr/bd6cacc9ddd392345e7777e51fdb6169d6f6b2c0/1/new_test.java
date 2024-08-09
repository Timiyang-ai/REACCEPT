@Test
  public void testSyncLocalCoreWithSharedStore_syncEquivalent() throws Exception {
    setupCluster(1);
    setupTestSharedClientForNode(getBlobStorageProviderTestInstance(storageClient), cluster.getJettySolrRunner(0));
    
    CloudSolrClient cloudClient = cluster.getSolrClient();
    
    collectionName = "sharedCol" + UUID.randomUUID();
    shardName = "shard" + UUID.randomUUID();
    setupSharedCollectionWithShardNames(collectionName, 1, 1, shardName);
    
    DocCollection collection = cloudClient.getZkStateReader().getClusterState().getCollection(collectionName);
    newReplica = collection.getReplicas().get(0);
    cc = getCoreContainer(newReplica.getNodeName());
    
    CoreStorageClient blobClientSpy = Mockito.spy(storageClient);
    // Add a document.
    SolrInputDocument doc = new SolrInputDocument();
    doc.setField("id", "1");
    doc.setField("cat", "cat123");
    UpdateRequest req = new UpdateRequest();
    req.add(doc);
    req.commit(cloudClient, collectionName);
    try {
      // we push and already have the latest updates so we should not pull here
      BlobStoreUtils.syncLocalCoreWithSharedStore(collectionName, newReplica.getCoreName(), shardName, cc);
      verify(blobClientSpy, never()).pullCoreMetadata(anyString(), anyString());
    } catch (Exception ex) { 
      fail("syncLocalCoreWithSharedStore failed with exception: " + ex.getMessage());
    }
  }