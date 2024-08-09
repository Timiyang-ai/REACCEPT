@Test
  public void lsrTest() throws IOException {
    FileInfo[] files = new FileInfo[4];

    TachyonFile fileA = TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileA",
        CacheType.CACHE, UnderStorageType.NO_PERSIST, 10);
    files[0] = mTfs.getInfo(fileA);
    TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testDir/testFileB", CacheType.CACHE,
        UnderStorageType.NO_PERSIST, 20);
    files[1] = mTfs.getInfo(mTfs.open(new TachyonURI("/testRoot/testDir")));
    files[2] = mTfs.getInfo(mTfs.open(new TachyonURI("/testRoot/testDir/testFileB")));
    TachyonFile fileC = TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileC",
        CacheType.NO_CACHE, UnderStorageType.PERSIST, 30);
    files[3] = mTfs.getInfo(fileC);
    mFsShell.run(new String[] {"lsr", "/testRoot"});
    String expected = "";
    String format = "%-10s%-25s%-15s%-5s\n";
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(10),
            TFsShell.convertMsToDate(files[0].getCreationTimeMs()), "In Memory",
            "/testRoot/testFileA");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(0),
            TFsShell.convertMsToDate(files[1].getCreationTimeMs()), "", "/testRoot/testDir");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(20),
            TFsShell.convertMsToDate(files[2].getCreationTimeMs()), "In Memory",
            "/testRoot/testDir/testFileB");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(30),
            TFsShell.convertMsToDate(files[3].getCreationTimeMs()), "Not In Memory",
            "/testRoot/testFileC");
    Assert.assertEquals(expected, mOutput.toString());
  }