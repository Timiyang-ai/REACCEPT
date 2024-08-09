@Test
  public void fileExists() throws IOException {
    String testFile = PathUtils.concatPath(mUnderfsAddress, "testFile");
    String testDir = PathUtils.concatPath(mUnderfsAddress, "testDir");
    Assert.assertFalse(mUfs.fileExists(testFile));
    createEmptyFile(testFile);
    mUfs.mkdirs(testDir, false);
    Assert.assertTrue(mUfs.fileExists(testFile));
    Assert.assertFalse(mUfs.fileExists(testDir));
  }