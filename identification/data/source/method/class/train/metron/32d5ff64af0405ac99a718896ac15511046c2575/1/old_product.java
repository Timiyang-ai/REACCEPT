@Override
  public List<byte[]> rowKeys(String profile, String entity, List<Object> groups, long durationAgo, TimeUnit unit) {
    List<byte[]> rowKeys = new ArrayList<>();

    // find the time horizon
    long endTime = System.currentTimeMillis();
    long startTime = endTime - unit.toMillis(durationAgo);

    // find the starting period and advance until the end time is reached
    ProfilePeriod period = new ProfilePeriod(startTime, periodDurationMillis, TimeUnit.MILLISECONDS);
    while(period.getStartTimeMillis() <= endTime) {

      byte[] k = rowKey(profile, entity, period, groups);
      rowKeys.add(k);

      // advance to the next period
      period = period.next();
    }

    return rowKeys;
  }