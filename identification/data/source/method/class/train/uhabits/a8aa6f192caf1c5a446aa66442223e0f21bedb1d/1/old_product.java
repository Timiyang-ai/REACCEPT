public boolean shouldShow()
    {
        long lastHintTimestamp = prefs.getLastHintTimestamp();
        return (DateUtils.getStartOfToday() > lastHintTimestamp);
    }