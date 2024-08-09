@Test
  public void testSyncLocalCoreWithSharedStore_missingBlob() throws Exception {
    setupCluster(1);
    setupTestSharedClientForNode(getBlobStorageProviderTestInstance(storageClient), cluster.getJettySolrRunner(0));
    
    collectionName = "sharedCol" + UUID.randomUUID();
    shardName = "shard" + UUID.randomUUID();
    CloudSolrClient cloudClient = cluster.getSolrClient();
    setupSharedCollectionWithShardNames(collectionName, 1, 1, shardName);
        
    DocCollection collection = cloudClient.getZkStateReader().getClusterState().getCollection(collectionName);
    newReplica = collection.getReplicas().get(0);
    cc = getCoreContainer(newReplica.getNodeName());
    
    SharedShardMetadataController sharedMetadataController = cc.getSharedStoreManager().getSharedShardMetadataController();
    sharedMetadataController.ensureMetadataNodeExists(collectionName, shardName);
    sharedMetadataController.updateMetadataValueWithVersion(collectionName, shardName, UUID.randomUUID().toString(), -1);
    try {
      BlobStoreUtils.syncLocalCoreWithSharedStore(collectionName, newReplica.getCoreName(), shardName, cc);
      fail("syncLocalCoreWithSharedStore should throw exception if shared store doesn't have the core.metadata file.");
    } catch (Exception ex){
      String expectedException = "cannot get core.metadata file from shared store";
      assertTrue(ex.getMessage().contains(expectedException)); 
    } 
  }