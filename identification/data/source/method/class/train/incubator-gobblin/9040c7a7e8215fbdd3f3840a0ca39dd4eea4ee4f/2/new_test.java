  @AfterClass
  public void cleanUp() throws Exception {
    FileUtils.deleteDirectory(new File(this.dagStateStoreDir));
  }