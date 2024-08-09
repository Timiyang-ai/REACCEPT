  @Test
  public void changeLocalFilePermission() throws IOException {
    // This test only works with normal users - superusers can operate on files whether or not they
    // have the proper permission bits set.
    assumeFalse(System.getProperty("user.name").equals("root"));
    File tempFile = mTestFolder.newFile("perm.txt");
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "---------");
    assertFalse(tempFile.canRead() || tempFile.canWrite() || tempFile.canExecute());
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "rwxrwxrwx");
    assertTrue(tempFile.canRead() && tempFile.canWrite() && tempFile.canExecute());
    // File deletion should fail, because we don't have write permissions
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "r--r--r--");
    assertTrue(tempFile.canRead());
    assertFalse(tempFile.canWrite());
    assertFalse(tempFile.canExecute());
    // expect a file permission error when we open it for writing
    mException.expect(IOException.class);
    @SuppressWarnings({"unused", "resource"})
    FileWriter fw = new FileWriter(tempFile);
    fail("opening a read-only file for writing should have failed");
  }