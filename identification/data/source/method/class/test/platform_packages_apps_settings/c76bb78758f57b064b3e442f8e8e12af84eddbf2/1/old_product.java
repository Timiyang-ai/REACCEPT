public synchronized void updateAnomalies(List<AppInfo> appInfos, int state) {
        if (!appInfos.isEmpty()) {
            final int size = appInfos.size();
            final String[] whereArgs = new String[size];
            for (int i = 0; i < size; i++) {
                whereArgs[i] = appInfos.get(i).packageName;
            }
            try (SQLiteDatabase db = mDatabaseHelper.getWritableDatabase()) {
                final ContentValues values = new ContentValues();
                values.put(ANOMALY_STATE, state);
                db.update(TABLE_ANOMALY, values, PACKAGE_NAME + " IN (" + TextUtils.join(",",
                        Collections.nCopies(appInfos.size(), "?")) + ")", whereArgs);
            }
        }
    }