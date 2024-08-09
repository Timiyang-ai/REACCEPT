public int[] getValues(Long fromTimestamp, Long toTimestamp)
    {
        buildCache(fromTimestamp, toTimestamp);
        if(fromTimestamp > toTimestamp) return new int[0];

        String query = "select value, timestamp from Checkmarks where " +
                "habit = ? and timestamp >= ? and timestamp <= ?";

        SQLiteDatabase db = Cache.openDatabase();
        String args[] = { habit.getId().toString(), fromTimestamp.toString(),
                toTimestamp.toString() };
        Cursor cursor = db.rawQuery(query, args);

        long day = DateHelper.millisecondsInOneDay;
        int nDays = (int) ((toTimestamp - fromTimestamp) / day) + 1;
        int[] checks = new int[nDays];

        if (cursor.moveToFirst())
        {
            do
            {
                long timestamp = cursor.getLong(1);
                int offset = (int) ((timestamp - fromTimestamp) / day);
                checks[nDays - offset - 1] = cursor.getInt(0);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return checks;
    }