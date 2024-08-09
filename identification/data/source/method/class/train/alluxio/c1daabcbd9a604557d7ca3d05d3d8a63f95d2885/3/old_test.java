@Test
  public void readTest5() throws Exception {
    String uniqPath = PathUtils.uniqPath();
    for (int k = MIN_LEN + DELTA; k <= MAX_LEN; k += DELTA) {
      AlluxioURI uri = new AlluxioURI(uniqPath + "/file_" + k);
      FileSystemTestUtils.createByteFile(mFileSystem, uri, mWriteAlluxio, k);

      URIStatus status = mFileSystem.getStatus(uri);
      InStreamOptions options = new InStreamOptions(status);
      long blockId = status.getBlockIds().get(0);
      BlockInfo info = AlluxioBlockStore.create().getInfo(blockId);
      WorkerNetAddress workerAddr = info.getLocations().get(0).getWorkerAddress();
      BlockInStream is =
          BlockInStream.create(FileSystemContext.get(), options.getBlockInfo(blockId),
              workerAddr, BlockInStreamSource.REMOTE, options);
      byte[] ret = new byte[k];
      int read = is.read(ret);
      Assert
          .assertTrue(BufferUtils.equalIncreasingByteArray(read, Arrays.copyOfRange(ret, 0, read)));
      is.close();
      Assert.assertTrue(mFileSystem.getStatus(uri).getInAlluxioPercentage() == 100);
    }
  }