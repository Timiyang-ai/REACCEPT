public int getValue(long timestamp)
    {
        Score s = get(timestamp);
        if(s == null) return 0;
        else return s.score;
    }