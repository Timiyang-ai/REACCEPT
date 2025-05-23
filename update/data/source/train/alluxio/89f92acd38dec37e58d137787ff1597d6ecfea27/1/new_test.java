@Test
  public void listStatus() throws IOException {
    String testDirNonEmpty = PathUtils.concatPath(mUnderfsAddress, "testDirNonEmpty1");
    String testDirNonEmptyChildDir = PathUtils.concatPath(testDirNonEmpty, "testDirNonEmpty2");
    String testDirNonEmptyChildFile = PathUtils.concatPath(testDirNonEmpty, "testDirNonEmptyF");
    String testDirNonEmptyChildDirFile =
        PathUtils.concatPath(testDirNonEmptyChildDir, "testDirNonEmptyChildDirF");
    mUfs.mkdirs(testDirNonEmpty, MkdirsOptions.defaults().setCreateParent(false));
    mUfs.mkdirs(testDirNonEmptyChildDir, MkdirsOptions.defaults().setCreateParent(false));
    createEmptyFile(testDirNonEmptyChildFile);
    createEmptyFile(testDirNonEmptyChildDirFile);
    String [] expectedResTopDir = new String[] {"testDirNonEmpty2", "testDirNonEmptyF"};
    // Some file systems may prefix with a slash
    String [] expectedResTopDir2 = new String[] {"/testDirNonEmpty2", "/testDirNonEmptyF"};
    Arrays.sort(expectedResTopDir);
    Arrays.sort(expectedResTopDir2);
    UfsStatus[] resTopDirStatus = mUfs.listStatus(testDirNonEmpty);
    String [] resTopDir = UfsStatus.convertToNames(resTopDirStatus);
    Arrays.sort(resTopDir);
    Assert.assertTrue(Arrays.equals(expectedResTopDir, resTopDir)
        || Arrays.equals(expectedResTopDir2, resTopDir));
    Assert.assertTrue(
        mUfs.listStatus(testDirNonEmptyChildDir)[0].getName().equals("testDirNonEmptyChildDirF")
            || mUfs.listStatus(testDirNonEmptyChildDir)[0].getName()
                .equals("/testDirNonEmptyChildDirF"));
    for (int i = 0; i < resTopDir.length; ++i) {
      Assert.assertEquals(
          mUfs.isDirectory(PathUtils.concatPath(testDirNonEmpty, resTopDirStatus[i].getName())),
          resTopDirStatus[i].isDirectory());
    }
  }