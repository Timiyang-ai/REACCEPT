@NonNull
    public synchronized final int[] getAllValues()
    {
        Repetition oldestRep = habit.getRepetitions().getOldest();
        if (oldestRep == null) return new int[0];

        Timestamp fromTimestamp = oldestRep.getTimestamp();
        Timestamp toTimestamp = DateUtils.getToday();

        return getValues(fromTimestamp, toTimestamp);
    }