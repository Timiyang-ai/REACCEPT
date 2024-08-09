public void record(long timeNano, int numEvents) {
    long leftEndPoint = bucket(timeNano);
    mSeries.put(leftEndPoint, mSeries.getOrDefault(leftEndPoint, 0) + numEvents);
  }