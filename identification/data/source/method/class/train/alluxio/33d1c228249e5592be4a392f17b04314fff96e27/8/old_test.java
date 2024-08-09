  @Test
  public void mount() throws Exception {
    AlluxioURI alluxioURI = new AlluxioURI("/hello");
    AlluxioURI ufsURI = createTempUfsDir("ufs/hello");
    mFileSystemMaster.mount(alluxioURI, ufsURI, MountContext.defaults());
  }