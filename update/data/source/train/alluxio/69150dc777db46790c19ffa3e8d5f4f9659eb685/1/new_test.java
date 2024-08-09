@Test
  public void changeLocalFilePermissionTest() throws IOException {
    File tempFile = mTestFolder.newFile("perm.txt");
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "---------");
    Assert.assertFalse(tempFile.canRead() || tempFile.canWrite() || tempFile.canExecute());
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "rwxrwxrwx");
    Assert.assertTrue(tempFile.canRead() && tempFile.canWrite() && tempFile.canExecute());
    // File deletion should fail, because we don't have write permissions
    FileUtils.changeLocalFilePermission(tempFile.getAbsolutePath(), "r--r--r--");
    Assert.assertTrue(tempFile.canRead());
    Assert.assertFalse(tempFile.canWrite());
    Assert.assertFalse(tempFile.canExecute());
    // expect a file permission error when we open it for writing
    mException.expect(IOException.class);
    @SuppressWarnings({"unused", "resource"})
    FileWriter fw = new FileWriter(tempFile);
    Assert.fail("opening a read-only file for writing should have failed");
  }