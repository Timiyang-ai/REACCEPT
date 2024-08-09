public final int[] getValues(long from, long to)
    {
        if (from > to) return new int[0];

        List<Checkmark> checkmarks = getByInterval(from, to);
        int values[] = new int[checkmarks.size()];

        int i = 0;
        for (Checkmark c : checkmarks)
            values[i++] = c.getValue();

        return values;
    }