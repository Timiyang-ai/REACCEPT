public final int[] getValues(long from, long to)
    {
        List<Score> scores = getByInterval(from, to);
        int[] values = new int[scores.size()];

        for(int i = 0; i < values.length; i++)
            values[i] = scores.get(i).getValue();

        return values;
    }