@Test
  public void testUploadPackage() throws Exception {
    // Insert mock Controller
    ScpUploader uploader = Mockito.spy(new ScpUploader());
    ScpController controller = Mockito.mock(ScpController.class);
    Mockito.doReturn(controller).when(uploader).getScpController();
    uploader.initialize(config);

    // Local file not exist
    Mockito.doReturn(false).when(uploader).isLocalFileExists(Mockito.anyString());
    Assert.assertNull(uploader.uploadPackage());
    Mockito.verify(controller, Mockito.never()).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());

    // Failed to create folder on remote
    Mockito.doReturn(true).when(uploader).isLocalFileExists(Mockito.anyString());
    Mockito.doReturn(false).when(controller).mkdirsIfNotExists(Mockito.anyString());
    Assert.assertNull(uploader.uploadPackage());
    Mockito.verify(controller, Mockito.never()).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());

    // Failed to copy file from local to remote
    Mockito.doReturn(true).when(controller).mkdirsIfNotExists(Mockito.anyString());
    Mockito.doReturn(false).when(controller).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());
    Assert.assertNull(uploader.uploadPackage());
    Mockito.verify(controller).copyFromLocalFile(Mockito.anyString(), Mockito.anyString());

    // Happy path
    Mockito.doReturn(true).when(controller).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());
    Assert.assertNotNull(uploader.uploadPackage());
    Mockito.verify(controller, Mockito.atLeastOnce()).copyFromLocalFile(
        Mockito.anyString(), Mockito.anyString());
  }