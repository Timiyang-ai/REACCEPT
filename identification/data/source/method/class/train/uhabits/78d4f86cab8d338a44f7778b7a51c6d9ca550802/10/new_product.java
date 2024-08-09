public final void writeCSV(Writer out) throws IOException
    {
        int values[];

        synchronized (this)
        {
            compute();
            values = getAllValues();
        }

        Timestamp timestamp = DateUtils.getToday();
        SimpleDateFormat dateFormat = DateFormats.getCSVDateFormat();

        for (int value : values)
        {
            String date = dateFormat.format(timestamp.toJavaDate());
            out.write(String.format("%s,%d\n", date, value));
            timestamp = timestamp.minus(1);
        }
    }