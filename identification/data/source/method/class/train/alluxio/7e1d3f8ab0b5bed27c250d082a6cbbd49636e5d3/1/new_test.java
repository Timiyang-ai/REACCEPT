@Test
  public void lsrTest() throws IOException, TachyonException {
    FileInfo[] files = new FileInfo[4];
    String testUser = "test_user_lsr";
    System.setProperty(Constants.SECURITY_LOGIN_USERNAME, testUser);

    TachyonFile fileA = TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileA",
        TachyonStorageType.STORE, UnderStorageType.NO_PERSIST, 10);
    files[0] = mTfs.getInfo(fileA);
    TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testDir/testFileB", TachyonStorageType.STORE,
        UnderStorageType.NO_PERSIST, 20);
    files[1] = mTfs.getInfo(mTfs.open(new TachyonURI("/testRoot/testDir")));
    files[2] = mTfs.getInfo(mTfs.open(new TachyonURI("/testRoot/testDir/testFileB")));
    TachyonFile fileC = TachyonFSTestUtils.createByteFile(mTfs, "/testRoot/testFileC",
        TachyonStorageType.NO_STORE, UnderStorageType.SYNC_PERSIST, 30);
    files[3] = mTfs.getInfo(fileC);
    mFsShell.run(new String[] {"lsr", "/testRoot"});
    String expected = "";
    String format = "%-10s%-25s%-15s%-15s%-5s\n";
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(10),
            TfsShell.convertMsToDate(files[0].getCreationTimeMs()), "In Memory",
            testUser, "/testRoot/testFileA");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(0),
            TfsShell.convertMsToDate(files[1].getCreationTimeMs()), "",
            testUser, "/testRoot/testDir");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(20),
            TfsShell.convertMsToDate(files[2].getCreationTimeMs()), "In Memory",
            testUser, "/testRoot/testDir/testFileB");
    expected +=
        String.format(format, FormatUtils.getSizeFromBytes(30),
            TfsShell.convertMsToDate(files[3].getCreationTimeMs()), "Not In Memory",
            testUser, "/testRoot/testFileC");
    Assert.assertEquals(expected, mOutput.toString());
  }