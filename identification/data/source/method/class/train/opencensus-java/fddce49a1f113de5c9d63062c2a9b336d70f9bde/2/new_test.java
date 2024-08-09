  @Test
  public void getResourceAsTempFile_deleteOnExit() throws IOException {
    Resources.getResourceAsTempFile("some_resource.txt", mockFile, new ByteArrayOutputStream());

    verify(mockFile).deleteOnExit();
  }