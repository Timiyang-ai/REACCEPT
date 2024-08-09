public void writeCSV(Writer out) throws IOException
    {
        computeAll();

        int values[] = getAllValues();
        long timestamp = DateUtils.getStartOfToday();
        SimpleDateFormat dateFormat = DateUtils.getCSVDateFormat();

        for (int value : values)
        {
            String date = dateFormat.format(new Date(timestamp));
            out.write(String.format("%s,%d\n", date, value));
            timestamp -= DateUtils.millisecondsInOneDay;
        }
    }