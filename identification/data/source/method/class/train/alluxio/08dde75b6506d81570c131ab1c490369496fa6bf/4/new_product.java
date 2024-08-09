public static HiveDatabase create(UdbContext udbContext, UdbConfiguration configuration) {
    String uris = configuration.get(Property.HIVE_METASTORE_URIS);
    if (uris.isEmpty()) {
      throw new IllegalArgumentException(
          "Hive metastore uris is not configured. Please set parameter: "
              + Property.HIVE_METASTORE_URIS.getFullName(HiveDatabaseFactory.TYPE));
    }
    String dbName = configuration.get(Property.DATABASE_NAME);
    if (dbName.isEmpty()) {
      throw new IllegalArgumentException(
          "Hive database name is not configured. Please set parameter: " + Property.DATABASE_NAME
              .getFullName(HiveDatabaseFactory.TYPE));
    }

    return new HiveDatabase(udbContext, configuration, dbName);
  }