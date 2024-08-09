public static void addKey(org.apache.hadoop.conf.Configuration hadoopConf, PropertyKey key) {
    if (Configuration.containsKey(key)) {
      hadoopConf.set(key.toString(), Configuration.get(key));
    }
  }