public int getVersion(@Feature int feature) {
    String selection = COLUMN_FEATURE + " = ?";
    String[] selectionArgs = {Integer.toString(feature)};
    try (Cursor cursor =
        databaseProvider
            .getReadableDatabase()
            .query(
                TABLE_NAME,
                new String[] {COLUMN_VERSION},
                selection,
                selectionArgs,
                /* groupBy= */ null,
                /* having= */ null,
                /* orderBy= */ null)) {
      if (cursor.getCount() == 0) {
        return VERSION_UNSET;
      }
      cursor.moveToNext();
      return cursor.getInt(/* COLUMN_VERSION index */ 0);
    }
  }