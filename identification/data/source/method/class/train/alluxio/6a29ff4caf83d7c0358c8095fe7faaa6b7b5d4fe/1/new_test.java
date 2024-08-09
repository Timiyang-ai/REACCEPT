@Test
  public void createStorageDirPathTest() throws IOException {
    File storageDir = new File(mTestFolder.getRoot(), "storageDir");
    File blockFile = new File(storageDir, "200");

    // When storage dir doesn't exists
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    Assert.assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
    Assert.assertEquals(
        PosixFilePermissions.fromString("rwxrwxrwx"),
        Files.getPosixFilePermissions(Paths.get(storageDir.getAbsolutePath())));

    // When storage dir exists
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    Assert.assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
  }