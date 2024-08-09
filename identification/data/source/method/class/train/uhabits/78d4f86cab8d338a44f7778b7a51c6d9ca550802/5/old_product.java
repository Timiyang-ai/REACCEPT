public void writeCSV(Writer out) throws IOException
    {
        computeAll();

        SimpleDateFormat dateFormat = DateUtils.getCSVDateFormat();

        String query = "select timestamp, value from checkmarks where habit = ? order by timestamp";
        String params[] = { habit.getId().toString() };

        SQLiteDatabase db = Cache.openDatabase();
        Cursor cursor = db.rawQuery(query, params);

        if(!cursor.moveToFirst()) return;

        do
        {
            String timestamp = dateFormat.format(new Date(cursor.getLong(0)));
            Integer value = cursor.getInt(1);
            out.write(String.format("%s,%d\n", timestamp, value));

        } while(cursor.moveToNext());

        cursor.close();
        out.close();
    }