@Test
  public void testUploadPackage() {
    ScpUploader uploader = Mockito.spy(new ScpUploader());
    Mockito.doReturn(true).when(uploader).isLocalFileExists(Mockito.anyString());
    ScpController controller = Mockito.mock(ScpController.class);
    Mockito.doReturn(controller).when(uploader).getScpController();
    Mockito.doReturn(true).when(controller).mkdirsIfNotExists(Mockito.anyString());
    uploader.initialize(config);
    Mockito.doReturn(true).when(controller).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());
    uploader.uploadPackage();
    Mockito.verify(controller, Mockito.atLeastOnce()).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());
  }