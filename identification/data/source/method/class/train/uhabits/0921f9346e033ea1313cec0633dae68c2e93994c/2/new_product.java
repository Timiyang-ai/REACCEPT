public void toggle(long timestamp)
    {
        timestamp = DateHelper.getStartOfDay(timestamp);

        if (contains(timestamp))
            delete(timestamp);
        else
            insert(timestamp);

        habit.scores.invalidateNewerThan(timestamp);
        habit.checkmarks.deleteNewerThan(timestamp);
        habit.streaks.deleteNewerThan(timestamp);
    }