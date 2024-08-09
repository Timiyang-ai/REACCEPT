@Test
  public void addKeyTest() {
    String key = "key";
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    Configuration.defaultInit();
    Configuration.set(key, "alluxioKey");

    System.setProperty(key, "systemKey");
    HdfsUnderFileSystemUtils.addKey(conf, key);
    Assert.assertEquals("systemKey", conf.get(key));

    System.clearProperty(key);
    HdfsUnderFileSystemUtils.addKey(conf, key);
    Assert.assertEquals("alluxioKey", conf.get(key));
  }