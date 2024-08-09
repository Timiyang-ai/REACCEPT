protected void prepareConfiguration(String path, TachyonConf tachyonConf, Configuration config) {
    String ufsHdfsImpl =
        mTachyonConf.get(Constants.UNDERFS_HDFS_IMPL,
            "org.apache.hadoop.hdfs.DistributedFileSystem");
    config.set("fs.hdfs.impl", ufsHdfsImpl);

    // To disable the instance cache for hdfs client, otherwise it causes the
    // FileSystem closed exception. Being configurable for unit/integration
    // test only, and not expose to the end-user currently.
    config.set("fs.hdfs.impl.disable.cache",
        System.getProperty("fs.hdfs.impl.disable.cache", "false"));
  }