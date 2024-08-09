public static void removeVersion(SQLiteDatabase writableDatabase, @Feature int feature) {
    if (!tableExists(writableDatabase, TABLE_NAME)) {
      return;
    }
    writableDatabase.delete(TABLE_NAME, WHERE_FEATURE_EQUALS, featureArgument(feature));
  }