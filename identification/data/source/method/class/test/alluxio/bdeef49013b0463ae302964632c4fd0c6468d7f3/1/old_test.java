@Test
  public void cacheBlockTest() throws IOException {
    int fileLen = USER_QUOTA_UNIT_BYTES + 4;
    int fid = TestUtils.createByteFile(mTfs, "/cacheBlockTest", WriteType.THROUGH, fileLen);
    long usedBytes = mLocalTachyonCluster.getMasterInfo().getWorkersInfo().get(0).getUsedBytes();
    Assert.assertEquals(0, usedBytes);

    byte[] content = new byte[fileLen];
    TachyonFS tfs1 = mLocalTachyonCluster.getClient();
    tfs1.getFile(fid).getInStream(ReadType.CACHE).read(content);
    usedBytes = mLocalTachyonCluster.getMasterInfo().getWorkersInfo().get(0).getUsedBytes();
    Assert.assertEquals(fileLen, usedBytes);

    TachyonFS tfs2 = mLocalTachyonCluster.getClient();
    tfs2.getFile(fid).getInStream(ReadType.CACHE).read(content);
    usedBytes = mLocalTachyonCluster.getMasterInfo().getWorkersInfo().get(0).getUsedBytes();
    Assert.assertEquals(fileLen, usedBytes);
  }