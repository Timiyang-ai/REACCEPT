@NonNull
    public Repetition toggle(long timestamp)
    {
        if(habit.isNumerical())
            throw new IllegalStateException("habit must NOT be numerical");

        timestamp = DateUtils.getStartOfDay(timestamp);
        Repetition rep = getByTimestamp(timestamp);

        if (rep != null) remove(rep);
        else
        {
            rep = new Repetition(timestamp, Checkmark.CHECKED_EXPLICITLY);
            add(rep);
        }

        habit.invalidateNewerThan(timestamp);
        return rep;
    }