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
    Assert.assertFalse(mUfs.isDirectory(testDirEmpty));
    try {
      mUfs.delete(testDirNonEmpty, false);
    } catch (IOException e) {
      // Some File systems may throw IOException
    }
    Assert.assertTrue(mUfs.isDirectory(testDirNonEmpty));
    mUfs.delete(testDirNonEmpty, true);
    Assert.assertFalse(mUfs.isDirectory(testDirNonEmpty));
    Assert.assertFalse(mUfs.isDirectory(testDirNonEmptyChildDir));
    Assert.assertFalse(mUfs.isFile(testDirNonEmptyChildFile));
    Assert.assertFalse(mUfs.isFile(testDirNonEmptyChildDirFile));
  }