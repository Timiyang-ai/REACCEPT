public static HiveDatabase create(Map<String, String> configuration) throws IOException {
    UnderFileSystem ufs;
    if (URIUtils.isLocalFilesystem(ServerConfiguration
        .get(PropertyKey.MASTER_MOUNT_TABLE_ROOT_UFS))) {
      ufs = UnderFileSystem.Factory
          .create("/", UnderFileSystemConfiguration.defaults(ServerConfiguration.global()));
    } else {
      ufs = UnderFileSystem.Factory.createForRoot(ServerConfiguration.global());
    }
    HiveDataCatalog catalog = new HiveDataCatalog(ufs);
    // TODO(gpang): get rid of creating db
    catalog.createDatabase(DEFAULT_DB_NAME);

    Hive hive;
    try {
      HiveConf conf = new HiveConf();
      // TODO(gpang): use configuration keys passed in
      conf.set("hive.metastore.uris", "thrift://localhost:9083");
      hive = Hive.get(conf);
    } catch (HiveException e) {
      throw new IOException("Failed to create hive client: " + e.getMessage(), e);
    }
    return new HiveDatabase(catalog, hive);
  }