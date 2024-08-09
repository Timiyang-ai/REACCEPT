public boolean anchor(long key, long value) {
    for (Map<Long, Long> m : buckets) {
      if (m.containsKey(key)) {
        long currentValue = m.get(key);
        long newValue = currentValue ^ value;
        m.put(key, newValue);

        return (newValue == 0);
      }
    }
    return false;
  }