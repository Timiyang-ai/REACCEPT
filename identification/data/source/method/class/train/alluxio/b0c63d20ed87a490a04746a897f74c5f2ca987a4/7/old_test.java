@Test
  public void lsrTest() throws IOException {
    int fileIdA =
        TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileA", WriteType.MUST_CACHE, 10);
    TachyonFile[] files = new TachyonFile[4];
    files[0] = mTfs.getFile(fileIdA);
    TachyonFSTestUtils
        .createByteFile(mTfs, "/testRoot/testDir/testFileB", WriteType.MUST_CACHE, 20);
    files[1] = mTfs.getFile(new TachyonURI("/testRoot/testDir"));
    files[2] = mTfs.getFile(new TachyonURI("/testRoot/testDir/testFileB"));
    int fileIdC =
        TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileC", WriteType.THROUGH, 30);
    files[3] = mTfs.getFile(fileIdC);
    mFsShell.run(new String[] {"ls", "/testRoot"});
    StringBuilder expected = new StringBuilder(200);
    String format = "%-10s%-25s%-15s%-5s\n";
    expected
        .append(String.format(format, FormatUtils.getSizeFromBytes(10),
            TFsShell.convertMsToDate(files[0].getCreationTimeMs()), "In Memory",
            "/testRoot/testFileA"));
    expected.append(String.format(format, FormatUtils.getSizeFromBytes(0),
        TFsShell.convertMsToDate(files[1].getCreationTimeMs()), "", "/testRoot/testDir"));
    expected.append(String.format(format, FormatUtils.getSizeFromBytes(30),
        TFsShell.convertMsToDate(files[3].getCreationTimeMs()), "Not In Memory",
        "/testRoot/testFileC"));
    Assert.assertEquals(expected.toString(), mOutput.toString());
  }