@Test
  public void getLocalFileModeTest() throws IOException {
    File tmpDir = mTestFolder.newFolder("dir");
    File tmpFile777 = mTestFolder.newFile("dir/0777");
    tmpFile777.setReadable(true, false /* owner only */);
    tmpFile777.setWritable(true, false /* owner only */);
    tmpFile777.setExecutable(true, false /* owner only */);

    File tmpFile755 = mTestFolder.newFile("dir/0755");
    tmpFile755.setReadable(true, false /* owner only */);
    tmpFile755.setWritable(true, true /* owner only */);
    tmpFile755.setExecutable(true, false /* owner only */);

    File tmpFile444 = mTestFolder.newFile("dir/0444");
    tmpFile444.setReadOnly();

    Assert.assertEquals((short) 0777, FileUtils.getLocalFileMode(tmpFile777.getPath()));
    Assert.assertEquals((short) 0755, FileUtils.getLocalFileMode(tmpFile755.getPath()));
    Assert.assertEquals((short) 0444, FileUtils.getLocalFileMode(tmpFile444.getPath()));

    // Delete all of these.
    FileUtils.deletePathRecursively(tmpDir.getAbsolutePath());
  }