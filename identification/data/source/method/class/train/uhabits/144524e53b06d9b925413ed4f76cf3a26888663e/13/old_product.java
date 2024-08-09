@NonNull
    public final int[] getAllValues()
    {
        Repetition oldestRep = habit.getRepetitions().getOldest();
        if (oldestRep == null) return new int[0];

        Long fromTimestamp = oldestRep.getTimestamp();
        Long toTimestamp = DateUtils.getStartOfToday();

        return getValues(fromTimestamp, toTimestamp);
    }