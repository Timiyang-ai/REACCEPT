@NonNull
    private List<Score> groupsToAvgScores(HashMap<Long, ArrayList<Long>> groups)
    {
        List<Score> scores = new LinkedList<>();

        for (Long timestamp : groups.keySet())
        {
            long meanValue = 0L;
            ArrayList<Long> groupValues = groups.get(timestamp);

            for (Long v : groupValues) meanValue += v;
            meanValue /= groupValues.size();

            scores.add(new Score(habit, timestamp, (int) meanValue));
        }

        return scores;
    }