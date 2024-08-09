@Test
  public void loadMetadata() throws Exception {
    String ufsRoot = ServerConfiguration.get(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS);
    UnderFileSystem ufs = UnderFileSystem.Factory.createForRoot(ServerConfiguration.global());
    ufs.create(ufsRoot + "/xyz").close();
    mFileSystem.loadMetadata(new AlluxioURI("/xyz"));
    URIStatus status = mFileSystem.getStatus(new AlluxioURI("/xyz"));
    mLocalAlluxioCluster.stopFS();
    loadMetadataTestUtil(status);
    deleteFsMasterJournalLogs();
    loadMetadataTestUtil(status);
  }