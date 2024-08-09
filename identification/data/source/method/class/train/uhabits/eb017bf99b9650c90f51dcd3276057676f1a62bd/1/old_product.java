public Repetition getOldest()
    {
        return (Repetition) select().limit(1).executeSingle();
    }