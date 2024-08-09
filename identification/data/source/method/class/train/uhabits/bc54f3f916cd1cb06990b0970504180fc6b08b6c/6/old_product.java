public int getValue(long timestamp)
    {
        computeAll();
        String[] args = { habit.getId().toString(), Long.toString(timestamp) };
        return SQLiteUtils.intQuery("select score from Score where habit = ? and timestamp = ?", args);
    }