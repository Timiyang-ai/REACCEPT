public static int getVersion(SQLiteDatabase database, @Feature int feature, String instanceUid)
      throws DatabaseIOException {
    try {
      if (!tableExists(database, TABLE_NAME)) {
        return VERSION_UNSET;
      }
      try (Cursor cursor =
          database.query(
              TABLE_NAME,
              new String[] {COLUMN_VERSION},
              WHERE_FEATURE_AND_INSTANCE_UID_EQUALS,
              featureAndInstanceUidArguments(feature, instanceUid),
              /* groupBy= */ null,
              /* having= */ null,
              /* orderBy= */ null)) {
        if (cursor.getCount() == 0) {
          return VERSION_UNSET;
        }
        cursor.moveToNext();
        return cursor.getInt(/* COLUMN_VERSION index */ 0);
      }
    } catch (SQLException e) {
      throw new DatabaseIOException(e);
    }
  }