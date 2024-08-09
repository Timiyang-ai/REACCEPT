public static Configuration prepareConfiguration(String path, Map<String, String> ufsConf) {
    Configuration hadoopConf = new Configuration();

    // On Hadoop 2.x this is strictly unnecessary since it uses ServiceLoader to automatically
    // discover available file system implementations. However this configuration setting is
    // required for earlier Hadoop versions plus it is still honoured as an override even in 2.x so
    // if present propagate it to the Hadoop configuration
    String ufsHdfsImpl = UnderFileSystemUtils.getValue(PropertyKey.UNDERFS_HDFS_IMPL, ufsConf);
    if (!StringUtils.isEmpty(ufsHdfsImpl)) {
      hadoopConf.set("fs.hdfs.impl", ufsHdfsImpl);
    }

    // Disable hdfs client caching so that input configuration is respected. Configurable from
    // system property
    hadoopConf.set("fs.hdfs.impl.disable.cache",
        System.getProperty("fs.hdfs.impl.disable.cache", "true"));
    return hadoopConf;
  }