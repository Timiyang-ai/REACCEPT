@NonNull
    static ArrayList<Interval> buildIntervals(@NonNull Frequency freq,
                                              @NonNull Repetition[] reps)
    {
        long day = DateUtils.millisecondsInOneDay;
        int num = freq.getNumerator();
        int den = freq.getDenominator();

        ArrayList<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < reps.length - num + 1; i++)
        {
            Repetition first = reps[i];
            Repetition last = reps[i + num - 1];

            long distance = (last.getTimestamp() - first.getTimestamp()) / day;
            if (distance >= den) continue;

            long begin = first.getTimestamp();
            long center = last.getTimestamp();
            long end = begin + (den - 1) * day;
            intervals.add(new Interval(begin, center, end));
        }

        return intervals;
    }