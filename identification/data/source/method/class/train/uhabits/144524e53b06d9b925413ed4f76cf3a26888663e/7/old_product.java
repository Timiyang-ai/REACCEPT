@NonNull
    public int[] getAllValues()
    {
        Repetition oldestRep = habit.repetitions.getOldest();
        if(oldestRep == null) return new int[0];

        Long fromTimestamp = oldestRep.timestamp;
        Long toTimestamp = DateUtils.getStartOfToday();

        return getValues(fromTimestamp, toTimestamp);
    }