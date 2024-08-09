@Test
  public void loadTest() throws Exception {
    // write a file outside of Alluxio
    AlluxioURI filePath = new AlluxioURI(TEST_URI);
    FileOutStream os = mFileSystem.createFile(filePath,
        CreateFileOptions.defaults().setWriteType(WriteType.THROUGH));
    os.write((byte) 0);
    os.write((byte) 1);
    os.close();

    // check the file is completed but not in Alluxio
    URIStatus status = mFileSystem.getStatus(filePath);
    Assert.assertEquals(PersistenceState.PERSISTED.toString(), status.getPersistenceState());
    Assert.assertTrue(status.isCompleted());
    Assert.assertEquals(0, status.getInMemoryPercentage());

    // run the load job
    waitForJobToFinish(mJobMaster.run(new LoadConfig("/test", null)));

    // check the file is fully in memory
    status = mFileSystem.getStatus(filePath);
    Assert.assertEquals(100, status.getInMemoryPercentage());

    // a second load should work too, no worker is selected
    long jobId = mJobMaster.run(new LoadConfig("/test", null));
    Assert.assertTrue(mJobMaster.getStatus(jobId).getTaskInfoList().isEmpty());
  }