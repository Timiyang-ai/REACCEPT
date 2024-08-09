@Test
  public void deleteFileTest() throws IOException, TachyonException {
    String uniqPath = PathUtils.uniqPath();

    for (int k = 0; k < 5; k ++) {
      TachyonURI fileURI = new TachyonURI(uniqPath + k);
      TachyonFile f = TachyonFSTestUtils.createByteFile(sTfs, fileURI.getPath(), k, sWriteBoth);
      Assert.assertTrue(sTfs.getInfo(f).getInMemoryPercentage() == 100);
      Assert.assertNotNull(sTfs.getInfo(f));
    }

    for (int k = 0; k < 5; k ++) {
      TachyonURI fileURI = new TachyonURI(uniqPath + k);
      TachyonFile f = sTfs.open(fileURI);
      sTfs.delete(f);
      mThrown.expect(FileDoesNotExistException.class);
      sTfs.getInfo(f);
    }
  }