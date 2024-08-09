@Test
  public void createUfsFileTest() throws Exception {
    UnderFileSystem mockUfs = Mockito.mock(UnderFileSystem.class);
    OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
    Mockito.when(mockUfs.create(Mockito.anyString())).thenReturn(mockOutputStream);
    PowerMockito.mockStatic(UnderFileSystem.class);
    BDDMockito.given(UnderFileSystem.get(Mockito.anyString(), Mockito.any(Configuration.class)))
        .willReturn(mockUfs);

    String testFile = "test";
    UnderFileSystemManager manager = new UnderFileSystemManager();
    manager.createFile(testFile);
    Mockito.verify(mockUfs).create(Mockito.contains(testFile));
  }