public final void writeCSV(Writer out) throws IOException
    {
        computeAll();

        int values[] = getAllValues();
        long timestamp = DateUtils.getStartOfToday();
        SimpleDateFormat dateFormat = DateFormats.getCSVDateFormat();

        for (int value : values)
        {
            String date = dateFormat.format(new Date(timestamp));
            out.write(String.format("%s,%d\n", date, value));
            timestamp -= DateUtils.millisecondsInOneDay;
        }
    }