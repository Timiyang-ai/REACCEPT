public boolean shouldShow()
    {
        Timestamp today = DateUtils.getToday();
        Timestamp lastHintTimestamp = prefs.getLastHintTimestamp();
        return (lastHintTimestamp.isOlderThan(today));
    }