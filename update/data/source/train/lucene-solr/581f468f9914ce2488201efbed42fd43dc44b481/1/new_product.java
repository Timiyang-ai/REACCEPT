public static void syncLocalCoreWithSharedStore(String collectionName, String coreName, String shardName, CoreContainer coreContainer,
                                                  SharedShardVersionMetadata shardVersionMetadata, boolean isLeaderPulling) throws SolrException {
    assert coreContainer.isZooKeeperAware();

    ZkController zkController = coreContainer.getZkController();
    DocCollection collection = zkController.getClusterState().getCollection(collectionName);
    CoreStorageClient blobClient = coreContainer.getSharedStoreManager().getBlobStorageProvider().getClient();
    log.info("sync initialized for collection=" + collectionName + " shard=" + shardName + " coreName=" + coreName);

    Slice shard = collection.getSlicesMap().get(shardName);
    if (shard != null) {
      try {
        String sharedStoreName = (String) shard.get(ZkStateReader.SHARED_SHARD_NAME);
        SharedCoreConcurrencyController concurrencyController = coreContainer.getSharedStoreManager().getSharedCoreConcurrencyController();
        if (SharedShardMetadataController.METADATA_NODE_DEFAULT_VALUE.equals(shardVersionMetadata.getMetadataSuffix())) {
          //no-op pull
          BlobCoreMetadata emptyBlobCoreMetadata = BlobCoreMetadataBuilder.buildEmptyCoreMetadata(sharedStoreName);
          concurrencyController.updateCoreVersionMetadata(collectionName, shardName, coreName, shardVersionMetadata, emptyBlobCoreMetadata, isLeaderPulling);
          log.info("sync successful, nothing to pull, collection=" + collectionName + " shard=" + shardName + " coreName=" + coreName);
          return;
        }
        concurrencyController.recordState(collectionName, shardName, coreName, SharedCoreStage.BlobPullStarted);
        try {
          // Get blob metadata
          String blobCoreMetadataName = BlobStoreUtils.buildBlobStoreMetadataName(shardVersionMetadata.getMetadataSuffix());
          BlobCoreMetadata blobstoreMetadata = blobClient.pullCoreMetadata(sharedStoreName, blobCoreMetadataName);
          if (null == blobstoreMetadata) {
            // Zookepeer and blob are out of sync, could be due to eventual consistency model in blob or something else went wrong.
            throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
                "cannot get core.metadata file from shared store, blobCoreMetadataName=" + blobCoreMetadataName +
                    " shard=" + shardName +
                    " collectionName=" + collectionName +
                    " sharedStoreName=" + sharedStoreName);
          } else if (blobstoreMetadata.getIsDeleted()) {
            throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
                "core.metadata file is marked deleted in shared store, blobCoreMetadataName=" + blobCoreMetadataName +
                    " shard=" + shardName +
                    " collectionName=" + collectionName +
                    " sharedStoreName=" + sharedStoreName);
          } else if (blobstoreMetadata.getIsCorrupt()) {
            log.warn("core.Metadata file is marked corrpt, skipping sync, collection=" + collectionName +
                " shard=" + shardName + " coreName=" + coreName + " sharedStoreName=" + sharedStoreName);
            return;
          }

          // Get local metadata + resolve with blob metadata
          ServerSideMetadata serverMetadata = new ServerSideMetadata(coreName, coreContainer);
          SharedMetadataResolutionResult resolutionResult = SharedStoreResolutionUtil.resolveMetadata(serverMetadata, blobstoreMetadata);
          PushPullData pushPullData = new PushPullData.Builder()
              .setCollectionName(collectionName)
              .setShardName(shardName)
              .setCoreName(coreName)
              .setSharedStoreName(sharedStoreName)
              .build();

          if (resolutionResult.getFilesToPull().size() > 0) {
            BlobDeleteManager deleteManager = coreContainer.getSharedStoreManager().getBlobDeleteManager();
            CorePushPull cp = new CorePushPull(blobClient, deleteManager, pushPullData, resolutionResult, serverMetadata, blobstoreMetadata);
            cp.pullUpdateFromBlob(/* waitForSearcher */ true);
            concurrencyController.updateCoreVersionMetadata(pushPullData.getCollectionName(), pushPullData.getShardName(), pushPullData.getCoreName(),
                shardVersionMetadata, blobstoreMetadata, isLeaderPulling);
          } else {
            log.warn(String.format("Why there are no files to pull even when we do not match with the version in zk? collection=%s shard=%s core=%s",
                collectionName, shardName, coreName));
          }
        } finally {
          concurrencyController.recordState(collectionName, shardName, coreName, SharedCoreStage.BlobPullFinished);
        }
      } catch (Exception ex) {
        // wrap every thrown exception in a solr exception
        throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Error occured pulling shard=" + shardName + " collection=" + collectionName + " from shared store " + ex);
      }
    } else {
      throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Sync requested for unknown shard=" + shardName + " in collection=" + collectionName);
    }

  }