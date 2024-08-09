@Test
  public void handleGetReplicasForBlobIdTest()
      throws InstantiationException, IOException, JSONException, RestServiceException, URISyntaxException {
    ClusterMap clusterMap = new MockClusterMap();
    AdminBlobStorageService adminBlobStorageService = getAdminBlobStorageService(clusterMap);
    List<PartitionId> partitionIds = clusterMap.getWritablePartitionIds();
    for (PartitionId partitionId : partitionIds) {
      createBlobIdAndTest(partitionId, adminBlobStorageService);
    }
  }