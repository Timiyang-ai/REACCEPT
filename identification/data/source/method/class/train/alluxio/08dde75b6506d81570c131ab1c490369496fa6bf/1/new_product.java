public static HiveDatabase create(UdbContext udbContext, UdbConfiguration configuration)
      throws IOException {
    String uris = configuration.get(Property.HIVE_METASTORE_URIS);
    if (uris.isEmpty()) {
      throw new IOException("Hive metastore uris is not configured. Please set parameter: "
          + Property.HIVE_METASTORE_URIS.getFullName(HiveDatabaseFactory.TYPE));
    }
    String dbName = configuration.get(Property.DATABASE_NAME);
    if (dbName.isEmpty()) {
      throw new IOException("Hive database name is not configured. Please set parameter: "
          + Property.DATABASE_NAME.getFullName(HiveDatabaseFactory.TYPE));
    }

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
    catalog.createDatabase(dbName);

    Hive hive;
    try {
      HiveConf conf = new HiveConf();
      conf.set("hive.metastore.uris", uris);
      hive = Hive.get(conf);
    } catch (HiveException e) {
      throw new IOException("Failed to create hive client: " + e.getMessage(), e);
    }
    return new HiveDatabase(udbContext, configuration, catalog, hive, dbName);
  }