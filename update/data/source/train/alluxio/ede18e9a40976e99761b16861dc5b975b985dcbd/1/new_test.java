@Test
  public void getTest() throws IOException {
    PowerMockito.spy(UnderFileSystemCluster.class);

    Mockito.when(UnderFileSystemCluster.getUnderFilesystemCluster(BASE_DIR))
        .thenReturn(mUnderFileSystemCluster);

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSCluster",
        (UnderFileSystemCluster) null);

    Mockito.when(mUnderFileSystemCluster.isStarted()).thenReturn(false);

    // execute test
    UnderFileSystemCluster.get(BASE_DIR);

    UnderFileSystemCluster underFSCluster = Whitebox.getInternalState(UnderFileSystemCluster
        .class, "sUnderFSCluster");

    Assert.assertSame(mUnderFileSystemCluster, underFSCluster);

    Mockito.verify(underFSCluster).start();
    Mockito.verify(underFSCluster).registerJVMOnExistHook();
  }