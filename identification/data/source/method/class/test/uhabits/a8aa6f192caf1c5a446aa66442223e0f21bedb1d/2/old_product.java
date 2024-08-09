public String pop()
    {
        int next = prefs.getLastHintNumber() + 1;
        if (next >= hints.length) return null;

        prefs.updateLastHint(next, DateUtils.getStartOfToday());
        return hints[next];
    }