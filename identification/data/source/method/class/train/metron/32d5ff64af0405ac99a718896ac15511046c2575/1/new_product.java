@Override
  public List<byte[]> rowKeys(String profile, String entity, List<Object> groups, long start, long end) {
    List<byte[]> rowKeys = new ArrayList<>();

    // be forgiving of out-of-order start and end times; order is critical to this algorithm
    end = Math.max(start, end);
    start = Math.min(start, end);

    // find the starting period and advance until the end time is reached
    ProfilePeriod period = new ProfilePeriod(start, periodDurationMillis, TimeUnit.MILLISECONDS);
    while(period.getStartTimeMillis() <= end) {

      byte[] k = rowKey(profile, entity, period, groups);
      rowKeys.add(k);

      // advance to the next period
      period = period.next();
    }

    return rowKeys;
  }