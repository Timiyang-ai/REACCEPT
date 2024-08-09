@Test
  public void getReplicasTest() throws Exception {
    List<? extends PartitionId> partitionIds = CLUSTER_MAP.getWritablePartitionIds(null);
    for (PartitionId partitionId : partitionIds) {
      String originalReplicaStr = partitionId.getReplicaIds().toString().replace(", ", ",");
      BlobId blobId = new BlobId(CommonTestUtils.getCurrentBlobIdVersion(), BlobId.BlobIdType.NATIVE,
          ClusterMapUtils.UNKNOWN_DATACENTER_ID, Account.UNKNOWN_ACCOUNT_ID, Container.UNKNOWN_CONTAINER_ID,
          partitionId, false, BlobId.BlobDataType.DATACHUNK);
      MockRestResponseChannel restResponseChannel = new MockRestResponseChannel();
      ReadableStreamChannel channel = getReplicasHandler.getReplicas(blobId.getID(), restResponseChannel);
      assertEquals("Unexpected response status", ResponseStatus.Ok, restResponseChannel.getStatus());
      assertEquals("Unexpected Content-Type", "application/json",
          restResponseChannel.getHeader(RestUtils.Headers.CONTENT_TYPE));
      String returnedReplicasStr = RestTestUtils.getJsonizedResponseBody(channel)
          .get(GetReplicasHandler.REPLICAS_KEY)
          .toString()
          .replace("\"", "");
      assertEquals("Replica IDs returned for the BlobId do no match with the replicas IDs of partition",
          originalReplicaStr, returnedReplicasStr);
    }
  }