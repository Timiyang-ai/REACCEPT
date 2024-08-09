public static void addKey(org.apache.hadoop.conf.Configuration hadoopConf, String key) {
    if (System.getProperty(key) != null) {
      hadoopConf.set(key, System.getProperty(key));
    } else if (Configuration.get(key) != null) {
      hadoopConf.set(key, Configuration.get(key));
    }
  }