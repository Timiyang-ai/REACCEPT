@Test
  public void getReplicasTest() throws Exception {
    List<? extends PartitionId> partitionIds = CLUSTER_MAP.getWritablePartitionIds();
    for (PartitionId partitionId : partitionIds) {
      String originalReplicaStr = partitionId.getReplicaIds().toString().replace(", ", ",");
      BlobId blobId = new BlobId(partitionId);
      MockRestResponseChannel restResponseChannel = new MockRestResponseChannel();
      ReadableStreamChannel channel = getReplicasHandler.getReplicas(blobId.getID(), restResponseChannel);
      assertEquals("Unexpected response status", ResponseStatus.Ok, restResponseChannel.getStatus());
      assertEquals("Unexpected Content-Type", "application/json",
          restResponseChannel.getHeader(RestUtils.Headers.CONTENT_TYPE));
      String returnedReplicasStr =
          RestTestUtils.getJsonizedResponseBody(channel).getString(GetReplicasHandler.REPLICAS_KEY).replace("\"", "");
      assertEquals("Replica IDs returned for the BlobId do no match with the replicas IDs of partition",
          originalReplicaStr, returnedReplicasStr);
    }
  }