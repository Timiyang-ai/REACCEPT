@Test
  public void addKeyTest() {
    PropertyKey key = PropertyKey.HOME;
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    Configuration.set(key, "alluxioKey");

    System.setProperty(key.toString(), "systemKey");
    ConfigurationTestUtils.resetConfiguration();
    HdfsUnderFileSystemUtils.addKey(conf, key);
    Assert.assertEquals("systemKey", conf.get(key.toString()));

    System.clearProperty(key.toString());
    ConfigurationTestUtils.resetConfiguration();
    HdfsUnderFileSystemUtils.addKey(conf, key);
    Assert.assertEquals("alluxioKey", conf.get(key.toString()));
    ConfigurationTestUtils.resetConfiguration();
  }