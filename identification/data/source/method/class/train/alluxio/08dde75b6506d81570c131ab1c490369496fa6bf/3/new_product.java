public static HiveDatabase create(UdbContext udbContext, UdbConfiguration configuration) {
    String connectionUri = udbContext.getConnectionUri();
    if (connectionUri == null || connectionUri.isEmpty()) {
      throw new IllegalArgumentException(
          "Hive udb connection uri cannot be empty: " + connectionUri);
    }
    String hiveDbName = udbContext.getUdbDbName();
    if (hiveDbName == null || hiveDbName.isEmpty()) {
      throw new IllegalArgumentException("Hive database name cannot be empty: " + hiveDbName);
    }

    return new HiveDatabase(udbContext, configuration, connectionUri, hiveDbName);
  }