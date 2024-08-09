public void toggle(long timestamp)
    {
        timestamp = DateHelper.getStartOfDay(timestamp);

        if (contains(timestamp))
        {
            delete(timestamp);
        }
        else
        {
            Repetition rep = new Repetition();
            rep.habit = habit;
            rep.timestamp = timestamp;
            rep.save();
        }

        habit.scores.invalidateNewerThan(timestamp);
        habit.checkmarks.deleteNewerThan(timestamp);
        habit.streaks.deleteNewerThan(timestamp);
    }