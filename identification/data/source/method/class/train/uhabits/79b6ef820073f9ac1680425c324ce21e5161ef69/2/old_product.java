public int getTodayValue()
    {
        Checkmark today = getToday();
        if(today != null) return today.value;
        else return Checkmark.UNCHECKED;
    }