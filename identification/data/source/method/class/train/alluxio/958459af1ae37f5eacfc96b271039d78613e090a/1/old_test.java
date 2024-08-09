@Test
  public void testIsFile() throws IOException {
    String testFile = PathUtils.concatPath(mUnderfsAddress, "testFile");
    String testDir = PathUtils.concatPath(mUnderfsAddress, "testDir");
    Assert.assertFalse(mUfs.isFile(testFile));
    createEmptyFile(testFile);
    mUfs.mkdirs(testDir, false);
    Assert.assertTrue(mUfs.isFile(testFile));
    Assert.assertFalse(mUfs.isFile(testDir));
  }