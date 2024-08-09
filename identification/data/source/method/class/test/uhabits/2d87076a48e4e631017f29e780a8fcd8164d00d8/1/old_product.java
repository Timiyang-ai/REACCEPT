public final double[] getValues(long from, long to)
    {
        List<Score> scores = getByInterval(from, to);
        double[] values = new double[scores.size()];

        for(int i = 0; i < values.length; i++)
            values[i] = scores.get(i).getValue();

        return values;
    }