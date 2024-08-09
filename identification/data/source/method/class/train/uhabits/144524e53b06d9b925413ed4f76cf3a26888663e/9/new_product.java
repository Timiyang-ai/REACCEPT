public final int[] getValues(Timestamp from, Timestamp to)
    {
        if (from.isNewerThan(to)) return new int[0];

        List<Checkmark> checkmarks = getByInterval(from, to);
        int values[] = new int[checkmarks.size()];

        int i = 0;
        for (Checkmark c : checkmarks)
            values[i++] = c.getValue();

        return values;
    }