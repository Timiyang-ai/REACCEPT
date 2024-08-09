public int[] getAllValues()
    {
        Repetition oldestRep = habit.repetitions.getOldest();
        if(oldestRep == null) return new int[0];

        Long toTimestamp = DateHelper.getStartOfToday();
        Long fromTimestamp = oldestRep.timestamp;
        return getValues(fromTimestamp, toTimestamp);
    }