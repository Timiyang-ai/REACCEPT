public final int getTodayValue()
    {
        Checkmark today = getToday();
        if (today != null) return today.getValue();
        else return Checkmark.UNCHECKED;
    }