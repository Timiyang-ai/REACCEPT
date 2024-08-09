public List<String> getSliceKeys(boolean isPlatformSlice) {
        verifyIndexing();
        final String whereClause;

        if (isPlatformSlice) {
            whereClause = IndexColumns.PLATFORM_SLICE + " = 1";
        } else {
            whereClause = IndexColumns.PLATFORM_SLICE + " = 0";
        }

        final SQLiteDatabase database = mHelper.getReadableDatabase();
        final String[] columns = new String[]{IndexColumns.KEY};
        final List<String> keys = new ArrayList<>();

        try (final Cursor resultCursor = database.query(TABLE_SLICES_INDEX, columns, whereClause,
                null /* selection */, null /* groupBy */, null /* having */, null /* orderBy */)) {
            if (!resultCursor.moveToFirst()) {
                return keys;
            }

            do {
                keys.add(resultCursor.getString(0 /* key index */));
            } while (resultCursor.moveToNext());
        }

        return keys;
    }