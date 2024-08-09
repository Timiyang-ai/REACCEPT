public void invalidateNewerThan(long timestamp)
    {
        new Delete().from(Score.class)
                .where("habit = ?", habit.getId())
                .and("timestamp >= ?", timestamp)
                .execute();

        observable.notifyListeners();
    }