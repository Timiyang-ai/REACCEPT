@NonNull
    static ArrayList<Interval> buildIntervals(@NonNull Frequency freq,
                                              @NonNull Repetition[] reps)
    {
        int num = freq.getNumerator();
        int den = freq.getDenominator();

        ArrayList<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < reps.length - num + 1; i++)
        {
            Repetition first = reps[i];
            Repetition last = reps[i + num - 1];

            long distance = first.getTimestamp().daysUntil(last.getTimestamp());
            if (distance >= den) continue;

            Timestamp begin = first.getTimestamp();
            Timestamp center = last.getTimestamp();
            Timestamp end = begin.plus(den - 1);
            intervals.add(new Interval(begin, center, end));
        }

        return intervals;
    }