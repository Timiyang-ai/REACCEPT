@Test
  public void testGetExtensionFilesToLoad_with_load_list() throws IOException
  {
    final File extensionsDir = temporaryFolder.newFolder();
    final ExtensionsConfig config = new ExtensionsConfig()
    {
      @Override
      public List<String> getLoadList()
      {
        return Arrays.asList("mysql-metadata-storage", "druid-kafka-eight");
      }

      @Override
      public String getDirectory()
      {
        return extensionsDir.getAbsolutePath();
      }
    };
    final File mysql_metadata_storage = new File(extensionsDir, "mysql-metadata-storage");
    final File druid_kafka_eight = new File(extensionsDir, "druid-kafka-eight");
    final File random_extension = new File(extensionsDir, "random-extensions");
    mysql_metadata_storage.mkdir();
    druid_kafka_eight.mkdir();
    random_extension.mkdir();

    final File[] expectedFileList = new File[]{druid_kafka_eight, mysql_metadata_storage};
    final File[] actualFileList = Initialization.getExtensionFilesToLoad(config);
    Arrays.sort(actualFileList);
    Assert.assertArrayEquals(expectedFileList, actualFileList);
  }