@Test
  public void createStorageDirPathTest() throws IOException {
    File storageDir = new File(mTestFolder.getRoot(), "tmp");
    File blockFile = new File(storageDir, "200");

    // When storage dir not exists
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    Assert.assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
    Assert.assertEquals(
        PosixFilePermissions.fromString("rwxrwxrwx"),
        Files.getPosixFilePermissions(Paths.get(storageDir.getAbsolutePath())));

    // When storage dir exists
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    Assert.assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
  }