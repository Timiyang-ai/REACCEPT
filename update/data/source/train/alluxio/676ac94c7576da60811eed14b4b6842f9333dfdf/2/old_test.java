@Test
  public void deleteDir() throws IOException {
    String testDirEmpty = PathUtils.concatPath(mUnderfsAddress, "testDirEmpty");
    String testDirNonEmpty = PathUtils.concatPath(mUnderfsAddress, "testDirNonEmpty1");
    String testDirNonEmptyChildDir = PathUtils.concatPath(testDirNonEmpty, "testDirNonEmpty2");
    String testDirNonEmptyChildFile = PathUtils.concatPath(testDirNonEmpty, "testDirNonEmptyF");
    String testDirNonEmptyChildDirFile = PathUtils.concatPath(testDirNonEmptyChildDir,
        "testDirNonEmptyChildDirF");
    mUfs.mkdirs(testDirEmpty, false);
    mUfs.mkdirs(testDirNonEmpty, false);
    mUfs.mkdirs(testDirNonEmptyChildDir, false);
    createEmptyFile(testDirNonEmptyChildFile);
    createEmptyFile(testDirNonEmptyChildDirFile);
    mUfs.delete(testDirEmpty, false);
    Assert.assertFalse(mUfs.directoryExists(testDirEmpty));
    try {
      mUfs.delete(testDirNonEmpty, false);
    } catch (IOException e) {
      // Some File systems may throw IOException
    }
    Assert.assertTrue(mUfs.directoryExists(testDirNonEmpty));
    mUfs.delete(testDirNonEmpty, true);
    Assert.assertFalse(mUfs.directoryExists(testDirNonEmpty));
    Assert.assertFalse(mUfs.directoryExists(testDirNonEmptyChildDir));
    Assert.assertFalse(mUfs.fileExists(testDirNonEmptyChildFile));
    Assert.assertFalse(mUfs.fileExists(testDirNonEmptyChildDirFile));
  }