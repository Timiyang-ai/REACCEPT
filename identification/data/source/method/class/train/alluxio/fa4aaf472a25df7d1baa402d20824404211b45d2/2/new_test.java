@Test
  public void generateClientFileInfoTest() {
    InodeDirectory inodeDirectory = createInodeDirectory();
    String path = "/test/path";
    FileInfo info = inodeDirectory.generateClientFileInfo(path);
    Assert.assertEquals(inodeDirectory.getId(), info.getFileId());
    Assert.assertEquals(inodeDirectory.getName(), info.getName());
    Assert.assertEquals(path, info.getPath());
    Assert.assertEquals(null, info.getUfsPath());
    Assert.assertEquals(0, info.getLength());
    Assert.assertEquals(0, info.getBlockSizeBytes());
    Assert.assertEquals(inodeDirectory.getCreationTimeMs(), info.getCreationTimeMs());
    Assert.assertTrue(info.isCompleted());
    Assert.assertTrue(info.isFolder());
    Assert.assertEquals(inodeDirectory.isPinned(), info.isPinned());
    Assert.assertFalse(info.isCacheable());
    Assert.assertNull(info.getBlockIds());
    Assert.assertEquals(inodeDirectory.getLastModificationTimeMs(),
        info.getLastModificationTimeMs());
  }