@Test
  public void deleteFileTest() throws IOException, TException {
    String uniqPath = PathUtils.uniqPath();

    for (int k = 0; k < 5; k ++) {
      TachyonURI fileURI = new TachyonURI(uniqPath + k);
      TachyonFile f = TachyonFSTestUtils.createByteFile(sTfs, fileURI.getPath(), sWriteBoth, k);
      Assert.assertTrue(sTfs.getInfo(f).getInMemoryPercentage() == 100);
      Assert.assertNotNull(sTfs.getInfo(f));
    }

    for (int k = 0; k < 5; k ++) {
      TachyonURI fileURI = new TachyonURI(uniqPath + k);
      TachyonFile f = sTfs.open(fileURI);
      sTfs.delete(f);
      Assert.assertNull(sTfs.getInfo(f));
    }
  }