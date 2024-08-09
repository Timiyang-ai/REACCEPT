@NonNull
    protected int[] getValues(long from, long to, long divisor)
    {
        compute(from, to);

        divisor *= DateHelper.millisecondsInOneDay;
        Long offset = to + divisor - 1;

        String query = "select ((timestamp - ?) / ?) as time, avg(score) from Score " +
                "where habit = ? and timestamp >= ? and timestamp <= ? " +
                "group by time order by time desc";

        String params[] = { offset.toString(), Long.toString(divisor), habit.getId().toString(),
                Long.toString(from), Long.toString(to) };

        SQLiteDatabase db = Cache.openDatabase();
        Cursor cursor = db.rawQuery(query, params);

        if(!cursor.moveToFirst()) return new int[0];

        int k = 0;
        int[] scores = new int[cursor.getCount()];

        do
        {
            scores[k++] = (int) cursor.getFloat(1);
        }
        while (cursor.moveToNext());

        cursor.close();
        return scores;
    }