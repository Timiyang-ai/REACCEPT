@Test(expected = ISE.class)
  public void testGetExtensionFilesToLoad_with_non_exist_item_in_load_list() throws IOException
  {
    final File extensionsDir = temporaryFolder.newFolder();
    final ExtensionsConfig config = new ExtensionsConfig()
    {
      @Override
      public LinkedHashSet<String> getLoadList()
      {
        return Sets.newLinkedHashSet(Arrays.asList("mysql-metadata-storage", "druid-kafka-eight"));
      }

      @Override
      public String getDirectory()
      {
        return extensionsDir.getAbsolutePath();
      }
    };
    final File druid_kafka_eight = new File(extensionsDir, "druid-kafka-eight");
    final File random_extension = new File(extensionsDir, "random-extensions");
    druid_kafka_eight.mkdir();
    random_extension.mkdir();
    Initialization.getExtensionFilesToLoad(config);
  }