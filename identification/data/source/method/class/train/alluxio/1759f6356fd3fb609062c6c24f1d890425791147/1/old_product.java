public void record(long timeNano, int numEvents) {
    long leftEndPoint = bucket(timeNano);
    mSeries.put(leftEndPoint,
        (mSeries.containsKey(leftEndPoint) ? mSeries.get(leftEndPoint) : 0) + numEvents);
  }