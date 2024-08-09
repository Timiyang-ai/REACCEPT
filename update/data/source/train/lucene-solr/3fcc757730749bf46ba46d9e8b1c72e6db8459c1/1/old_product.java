public static void  syncLocalCoreWithSharedStore( String collectionName, String coreName, String shardName, CoreContainer  coreContainer) throws SolrException
  {
    assertTrue(coreContainer.isZooKeeperAware());

    ZkController zkController = coreContainer.getZkController();
    SharedShardMetadataController sharedMetadataController = zkController.getSharedShardMetadataController();
    DocCollection collection = zkController.getClusterState().getCollection(collectionName);
    CoreStorageClient blobClient = zkController.getBlobStorageProvider().getDefaultClient();
    log.info("sync intialized for collection=" + collectionName + " shard=" + shardName + " coreName=" + coreName);

    CoreSyncStatus syncStatus = CoreSyncStatus.FAILURE;

    Slice shard = collection.getSlicesMap().get(shardName);
    if (shard != null) {
      try {
        String sharedStoreName = (String)shard.get(ZkStateReader.SHARED_SHARD_NAME);
        // Fetch the latest metadata from ZK.
        // TODO: this can be optimized, depends on correct handling of leadership change.
        VersionedData data = sharedMetadataController.readMetadataValue(collectionName, shardName, false);

        Map<String, String> nodeUserData = (Map<String, String>) Utils.fromJSON(data.getData());
        String metadataSuffix = nodeUserData.get(SharedShardMetadataController.SUFFIX_NODE_NAME);
        if (SharedShardMetadataController.METADATA_NODE_DEFAULT_VALUE.equals(metadataSuffix)) {
          log.info("sync successful, nothing to pull, collection=" + collectionName + " shard=" + shardName + " coreName=" + coreName);
          return ;
        }
        // Get blob metadata
        String blobCoreMetadataName = BlobStoreUtils.buildBlobStoreMetadataName(metadataSuffix);
        BlobCoreMetadata blobstoreMetadata = blobClient.pullCoreMetadata(sharedStoreName, blobCoreMetadataName);
        if (null == blobstoreMetadata) {
          // Zookepeer and blob are out of sync, could be due to eventual consistency model in blob or something else went wrong.
          throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
              "cannot get core.metadata file from shared store, blobCoreMetadataName=" + blobCoreMetadataName +
              " shard=" + shardName +
              " collectionName=" + collectionName +
              " sharedStoreName=" + sharedStoreName );
        } else if (blobstoreMetadata.getIsDeleted()) {
          throw new SolrException(SolrException.ErrorCode.SERVER_ERROR,
              "core.metadata file is marked deleted in shared store, blobCoreMetadataName=" + blobCoreMetadataName +
              " shard=" + shardName +
              " collectionName=" + collectionName +
              " sharedStoreName=" + sharedStoreName );
        } else if (blobstoreMetadata.getIsCorrupt()) {
          log.warn("core.Metadata file is marked corrpt, skipping sync, collection=" + collectionName + 
              " shard=" + shardName + " coreName=" + coreName + " sharedStoreName=" + sharedStoreName );
          return ;
        }

        // Get local metadata + resolve with blob metadata
        ServerSideMetadata serverMetadata = new ServerSideMetadata(coreName, coreContainer);
        SharedMetadataResolutionResult resolutionResult = SharedStoreResolutionUtil.resolveMetadata(serverMetadata, blobstoreMetadata);
        PushPullData pushPullData = new PushPullData.Builder()
            .setCollectionName(collectionName)
            .setShardName(shardName)
            .setCoreName(coreName)
            .setSharedStoreName(sharedStoreName)
            .setLastReadMetadataSuffix(metadataSuffix)
            .setNewMetadataSuffix(BlobStoreUtils.generateMetadataSuffix())
            .setZkVersion(data.getVersion())
            .build();

        if (resolutionResult.getFilesToPull().size() > 0) {

          BlobDeleteManager deleteManager = zkController.getBlobDeleteManager();
          CorePushPull cp = new CorePushPull(blobClient, deleteManager, pushPullData, resolutionResult, serverMetadata, blobstoreMetadata);
          cp.pullUpdateFromBlob(/* waitForSearcher */ true);
        } else {
          log.info("sync successful, nothing to pull for collection=" + collectionName + " shard=" + shardName + " coreName=" + coreName);
        }
      } catch (Exception ex) {
        // wrap every thrown exception in a solr exception
        throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Error occured pulling shard=" + shardName + " collection=" + collectionName + " from shared store "+ ex);
      }
    } else {
      throw new SolrException(SolrException.ErrorCode.SERVER_ERROR, "Sync requested for unknown shard=" + shardName + " in collection=" + collectionName);
    }

  }