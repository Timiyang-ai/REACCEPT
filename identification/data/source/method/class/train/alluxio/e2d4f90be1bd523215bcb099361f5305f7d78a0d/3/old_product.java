public static void addKey(org.apache.hadoop.conf.Configuration hadoopConf, Configuration conf,
      String key) {
    if (System.getProperty(key) != null) {
      hadoopConf.set(key, System.getProperty(key));
    } else if (conf.get(key) != null) {
      hadoopConf.set(key, conf.get(key));
    }
  }