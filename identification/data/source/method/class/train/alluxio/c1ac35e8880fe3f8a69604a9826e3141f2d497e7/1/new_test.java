  @Test
  public void getBucketName() throws Exception {
    assertEquals("s3-bucket-name",
        UnderFileSystemUtils.getBucketName(new AlluxioURI("s3://s3-bucket-name/")));
    assertEquals("s3a_bucket_name",
        UnderFileSystemUtils.getBucketName(new AlluxioURI("s3a://s3a_bucket_name/")));
    assertEquals("a.b.c",
        UnderFileSystemUtils.getBucketName(new AlluxioURI("gs://a.b.c/folder/sub-folder/")));
    assertEquals("container&",
        UnderFileSystemUtils.getBucketName(new AlluxioURI("swift://container&/folder/file")));
    assertEquals("oss",
        UnderFileSystemUtils.getBucketName(new AlluxioURI("oss://oss/folder/.file")));
  }