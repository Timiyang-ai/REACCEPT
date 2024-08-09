@Test
  public void addKeyTest() {
    String key = "key";
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    Configuration configuration = new Configuration();
    configuration.set(key, "alluxioKey");

    System.setProperty(key, "systemKey");
    HdfsUnderFileSystemUtils.addKey(conf, configuration, key);
    Assert.assertEquals("systemKey", conf.get(key));

    System.clearProperty(key);
    HdfsUnderFileSystemUtils.addKey(conf, configuration, key);
    Assert.assertEquals("alluxioKey", conf.get(key));
  }