  @Test
  public void createFileId() throws Exception {
    long containerId = 1;
    long fileId = IdUtils.createFileId(containerId);
    assertNotEquals(-1, fileId);
  }