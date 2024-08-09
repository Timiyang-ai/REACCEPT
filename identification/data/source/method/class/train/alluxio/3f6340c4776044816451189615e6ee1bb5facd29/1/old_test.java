  @Test
  public void deletePathRecursively() throws IOException {
    File tmpDir = mTestFolder.newFolder("dir");
    File tmpDir1 = mTestFolder.newFolder("dir", "dir1");
    File tmpDir2 = mTestFolder.newFolder("dir", "dir2");

    File tmpFile1 = mTestFolder.newFile("dir/dir1/file1");
    File tmpFile2 = mTestFolder.newFile("dir/dir1/file2");
    File tmpFile3 = mTestFolder.newFile("dir/file3");

    // Delete all of these.
    FileUtils.deletePathRecursively(tmpDir.getAbsolutePath());

    assertFalse(tmpDir.exists());
    assertFalse(tmpDir1.exists());
    assertFalse(tmpDir2.exists());
    assertFalse(tmpFile1.exists());
    assertFalse(tmpFile2.exists());
    assertFalse(tmpFile3.exists());
  }