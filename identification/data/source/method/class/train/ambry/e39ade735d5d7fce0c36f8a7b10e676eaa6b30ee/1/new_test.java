@Test
  public void handleGetReplicasForBlobIdTest()
      throws InstantiationException, JSONException, RestServiceException, URISyntaxException {
    List<PartitionId> partitionIds = clusterMap.getWritablePartitionIds();
    for (PartitionId partitionId : partitionIds) {
      String originalReplicaStr = partitionId.getReplicaIds().toString().replace(", ", ",");
      BlobId blobId = new BlobId(partitionId);
      RestRequestInfo restRequestInfo = createGetReplicasForBlobIdRestRequestInfo(blobId.getID());
      adminBlobStorageService.handleGet(restRequestInfo);
      finishGetRequest(adminBlobStorageService, restRequestInfo);
      String returnedReplicasStr =
          getJsonizedResponseBody(restRequestInfo).getString(GetReplicasForBlobIdHandler.REPLICAS_KEY)
              .replace("\"", "");
      assertEquals("Replica IDs returned for the BlobId do no match with the replicas IDs of partition",
          originalReplicaStr, returnedReplicasStr);
    }
  }