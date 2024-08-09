@Test
  public void testGetExtensionFilesToLoad_with_load_list() throws IOException
  {
    final File extensionsDir = temporaryFolder.newFolder();

    final File absolutePathExtension = temporaryFolder.newFolder();

    final ExtensionsConfig config = new ExtensionsConfig()
    {
      @Override
      public List<String> getLoadList()
      {
        return Arrays.asList("mysql-metadata-storage", "druid-kafka-eight", absolutePathExtension.getAbsolutePath());
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

    final File[] expectedFileList = new File[]{mysql_metadata_storage, druid_kafka_eight, absolutePathExtension};
    final File[] actualFileList = Initialization.getExtensionFilesToLoad(config);
    Assert.assertArrayEquals(expectedFileList, actualFileList);
  }