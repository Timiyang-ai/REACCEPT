public void putDownload(Download download) throws DatabaseIOException {
    ensureInitialized();
    ContentValues values = new ContentValues();
    values.put(COLUMN_ID, download.action.id);
    values.put(COLUMN_TYPE, download.action.type);
    values.put(COLUMN_URI, download.action.uri.toString());
    values.put(COLUMN_STREAM_KEYS, encodeStreamKeys(download.action.streamKeys));
    values.put(COLUMN_CUSTOM_CACHE_KEY, download.action.customCacheKey);
    values.put(COLUMN_DATA, download.action.data);
    values.put(COLUMN_STATE, download.state);
    values.put(COLUMN_DOWNLOAD_PERCENTAGE, download.getDownloadPercentage());
    values.put(COLUMN_DOWNLOADED_BYTES, download.getDownloadedBytes());
    values.put(COLUMN_TOTAL_BYTES, download.getTotalBytes());
    values.put(COLUMN_FAILURE_REASON, download.failureReason);
    values.put(COLUMN_STOP_FLAGS, 0);
    values.put(COLUMN_NOT_MET_REQUIREMENTS, 0);
    values.put(COLUMN_MANUAL_STOP_REASON, download.manualStopReason);
    values.put(COLUMN_START_TIME_MS, download.startTimeMs);
    values.put(COLUMN_UPDATE_TIME_MS, download.updateTimeMs);
    try {
      SQLiteDatabase writableDatabase = databaseProvider.getWritableDatabase();
      writableDatabase.replaceOrThrow(TABLE_NAME, /* nullColumnHack= */ null, values);
    } catch (SQLiteException e) {
      throw new DatabaseIOException(e);
    }
  }