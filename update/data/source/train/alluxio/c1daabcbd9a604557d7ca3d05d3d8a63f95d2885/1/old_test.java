@Test
  public void createStorageDirPath() throws IOException {
    File storageDir = new File(mTestFolder.getRoot(), "storageDir");
    File blockFile = new File(storageDir, "200");

    // When storage dir doesn't exist
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
    assertEquals(
        PosixFilePermissions.fromString("rwxrwxrwx"),
        Files.getPosixFilePermissions(Paths.get(storageDir.getAbsolutePath())));

    // When storage dir exists
    FileUtils.createBlockPath(blockFile.getAbsolutePath());
    assertTrue(FileUtils.exists(storageDir.getAbsolutePath()));
  }