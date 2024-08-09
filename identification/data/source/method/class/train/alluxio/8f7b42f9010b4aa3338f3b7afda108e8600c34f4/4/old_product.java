protected void prepareConfiguration(String path, TachyonConf tachyonConf, Configuration config) {
    // On Hadoop 2.x this is strictly unnecessary since it uses ServiceLoader to automatically
    // discover available file system implementations. However this configuration setting is
    // required for earlier Hadoop versions plus it is still honoured as an override even in 2.x so
    // if present propagate it to the Hadoop configuration
    String ufsHdfsImpl = mTachyonConf.get(Constants.UNDERFS_HDFS_IMPL, null);
    if (!StringUtils.isEmpty(ufsHdfsImpl)) {
      config.set("fs.hdfs.impl", ufsHdfsImpl);
    }

    // To disable the instance cache for hdfs client, otherwise it causes the
    // FileSystem closed exception. Being configurable for unit/integration
    // test only, and not expose to the end-user currently.
    config.set("fs.hdfs.impl.disable.cache",
        System.getProperty("fs.hdfs.impl.disable.cache", "false"));

    HdfsUnderFileSystemUtils.addKey(config, tachyonConf, Constants.UNDERFS_HADOOP_CONFIGURATION);
  }