static void snapIntervalsTogether(@NonNull ArrayList<Interval> intervals)
    {
        for (int i = 1; i < intervals.size(); i++)
        {
            Interval curr = intervals.get(i);
            Interval prev = intervals.get(i - 1);

            int gap = prev.end.daysUntil(curr.begin) - 1;
            if (gap <= 0 || curr.end.minus(gap).isOlderThan(curr.center)) continue;
            intervals.set(i, new Interval(curr.begin.minus(gap), curr.center,
                curr.end.minus(gap)));
        }
    }