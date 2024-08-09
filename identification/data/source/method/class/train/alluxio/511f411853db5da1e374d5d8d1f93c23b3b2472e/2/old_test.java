@Test
  public void completeUfsFileTest() throws Exception {
    UnderFileSystem mockUfs = Mockito.mock(UnderFileSystem.class);
    OutputStream mockOutputStream = Mockito.mock(OutputStream.class);
    Mockito.when(mockUfs.create(Mockito.anyString())).thenReturn(mockOutputStream);
    PowerMockito.mockStatic(UnderFileSystem.class);
    BDDMockito.given(UnderFileSystem.get(Mockito.anyString(), Mockito.any(Configuration.class)))
        .willReturn(mockUfs);

    String testFile = "test";
    UnderFileSystemManager manager = new UnderFileSystemManager();
    long id = manager.createFile(testFile);
    Mockito.verify(mockUfs).create(Mockito.contains(testFile));
    manager.completeFile(id);
    Mockito.verify(mockUfs).rename(Mockito.contains(testFile), Mockito.eq(testFile));
  }