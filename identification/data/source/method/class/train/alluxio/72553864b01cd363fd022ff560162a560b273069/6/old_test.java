@Test
  public void getChildTest() {
    // large number of small files
    InodeDirectory inodeDirectory = createInodeDirectory();
    int nFiles = (int) 1E5;
    Inode[] inodes = new Inode[nFiles];
    for (int i = 0; i < nFiles; i++) {
      inodes[i] = createInodeFile(i + 1);
      inodeDirectory.addChild(inodes[i]);
    }

    Runtime runtime = Runtime.getRuntime();
    LOG.info(String.format("Used Memory = %dB when number of files = %d",
        runtime.totalMemory() - runtime.freeMemory(), nFiles));

    long start = System.currentTimeMillis();
    for (int i = 0; i < nFiles; i++) {
      Assert.assertEquals(inodes[i], inodeDirectory.getChild(createInodeFileId(i + 1)));
    }
    LOG.info(String.format("getChild(int fid) called sequentially %d times, cost %d ms", nFiles,
        System.currentTimeMillis() - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < nFiles; i++) {
      Assert.assertEquals(inodes[i], inodeDirectory.getChild(String.format("testFile%d", i + 1)));
    }
    LOG.info(String.format("getChild(String name) called sequentially %d times, cost %d ms", nFiles,
        System.currentTimeMillis() - start));
  }