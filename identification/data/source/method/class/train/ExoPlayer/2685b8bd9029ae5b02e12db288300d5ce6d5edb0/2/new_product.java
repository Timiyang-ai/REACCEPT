public static void removeVersion(
      SQLiteDatabase writableDatabase, @Feature int feature, String instanceUid)
      throws DatabaseIOException {
    try {
      if (!tableExists(writableDatabase, TABLE_NAME)) {
        return;
      }
      writableDatabase.delete(
          TABLE_NAME,
          WHERE_FEATURE_AND_INSTANCE_UID_EQUALS,
          featureAndInstanceUidArguments(feature, instanceUid));
    } catch (SQLException e) {
      throw new DatabaseIOException(e);
    }
  }