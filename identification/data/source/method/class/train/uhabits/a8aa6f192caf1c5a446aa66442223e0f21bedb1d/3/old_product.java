static void snapIntervalsTogether(@NonNull ArrayList<Interval> intervals)
    {
        long day = DateUtils.millisecondsInOneDay;

        for (int i = 1; i < intervals.size(); i++)
        {
            Interval curr = intervals.get(i);
            Interval prev = intervals.get(i - 1);

            long distance = curr.begin - prev.end - day;
            if (distance <= 0 || curr.end - distance < curr.center) continue;
            intervals.set(i, new Interval(curr.begin - distance, curr.center,
                curr.end - distance));
        }
    }